package com.rbf.UserProfileManagement.Server.Messaging;

import com.rbf.UserProfileManagement.Server.Repositories.PartnersRepository;
import com.rbf.UserProfileManagement.Server.Repositories.UserInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class Subscriber {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private PartnersRepository partnersRepository;
    @Autowired
    private Publisher publisher;

    // auth service must send a map with old and new usernames to this listener
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "UpdateUsernameUPAM", partitions = {"0"})})
    private void updateUsername(Map<String, String> usernames) {
        logger.info("Update Username Received Message in partition 0: " + usernames.get("new"));
        int usernameUpdated = userInformationRepository.updateUsername(usernames.get("new"), usernames.get("old")); //query to update username
        if(usernameUpdated == 1) { //if username was updated successfully
            logger.info("Username updated successfully in UPAM");
            //kafkaMapTemplate.send("UpdateUsernameSAT", 0, "UU", usernames); //continue saga to SAT
            publisher.updateUsernameSAT(usernames.get("new"), usernames.get("old"));
        }
        else if(usernameUpdated == 0) { //if username was not updated successfully
            logger.info("Username update failed in UPAM");
            // partition 1 belongs to the authentication listener for rollback
            // send back old username for rollback in auth service
            //kafkaMapTemplate.send("UpdateUsernameFailed", 1, "UUF", usernames);
            publisher.updateUsernameFailed(usernames.get("new"), usernames.get("old"));
        }
    }

    // rollback this services' username because of update failure in SAT Service
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "UpdateUsernameFailed", partitions = {"0"})})
    private void updateUsernameFailed(Map<String, String> usernames) {
        logger.info("Received Username rollback message in partition 0: " + usernames.get("new"));
        int usernameRolledBack = userInformationRepository.updateUsername(usernames.get("old"), usernames.get("new")); //rollback username
        if(usernameRolledBack == 1) { //if username was rolled back successfully
            logger.info("Username rolled back successfully in UPAM");
        }
        else{
            logger.info("Username rollback failed in UPAM. Retrying");
            usernameRolledBack = userInformationRepository.updateUsername(usernames.get("old"), usernames.get("new")); //rollback username
            logger.info("Retry Result is: " + usernameRolledBack);
        }
    }

    // "remove sessions" failed in SAT Service. Need to readd partner for rollback
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "DeleteSessionsFailed", partitions = {"0"})})
    private void deleteSessionsFailed(Map<String, String> data) { //<user_id (owning partner), restored partner username>
        logger.info("Received Message in deleteSessionsFailed partition 0: ");
        UUID partner_id = UUID.randomUUID();
        List<Object[]> feedback = partnersRepository.addPartner(partner_id, data.get("user_id"), data.get("partner")); //rollback partner
        if(feedback.size() == 1) { //if partner was rolled back successfully
            logger.info("Partner rolled back successfully in UPAM");
        }
        else{
            logger.info("Partner rollback failed in UPAM. Retrying");
            feedback = partnersRepository.addPartner(partner_id, data.get("user_id"), data.get("partner")); //rollback partner
            logger.info("Retry Result is: " + feedback.size());
        }
    }

    // add a partner to a user from data given from SAPR Service
    // SAPR should give map with keys user_id, partner, requestor, requestee
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "AddPartner", partitions = {"0"})})
    private void addPartner(Map<String, String> data) {
        logger.info("Received Message in partition 0 addpartner: ");
        UUID partner_id = UUID.randomUUID();
        List<Object[]> feedback = partnersRepository.addPartner(partner_id, data.get("user_id"), data.get("partner")); //add partner
        if(feedback.size() == 1) { //if partner was added successfully
            logger.info("Partner added successfully in UPAM");
        }
        else if(feedback.size() == 0) { //if partner was not added successfully
            logger.info("Partner add failed in UPAM. Retrying");
            feedback = partnersRepository.addPartner(partner_id, data.get("user_id"), data.get("partner")); //add partner
            logger.info("Retry Result is: " + feedback.size());
            if(feedback.size() == 1) {
                logger.info("Partner added successfully in UPAM");
            }
            else {
                logger.info("Partner add failed in UPAM. Sending requestor and requestee for rollback");
                publisher.addPartnerFailed(data.get("requestor"), data.get("requestee"));
            }
        }
    }

}