package com.rbf.UserProfileManagement.Server.Messaging;

import com.rbf.UserProfileManagement.Server.Models.UserInformationModel;
import com.rbf.UserProfileManagement.Server.Repositories.PartnersRepository;
import com.rbf.UserProfileManagement.Server.Repositories.UserInformationRepository;
import com.rbf.common.payload.MessageWrapper;
import com.rbf.common.payload.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

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
    @KafkaListener(id = "UUC", topicPartitions = {@TopicPartition(topic = "UpdateUsernameUPAM", partitions = {"0"})})
    private void updateUsername(Payload usernames) {
        logger.info("Update Username Received Message in partition 0: " + usernames.getNewUsername());
        int usernameUpdated = userInformationRepository.updateUsername(usernames.getNewUsername(), usernames.getOldUsername()); //query to update username
        if(usernameUpdated == 1) { //if username was updated successfully
            logger.info("Username updated successfully in UPAM");
            publisher.updateUsernameSAT(usernames.getNewUsername(), usernames.getOldUsername());
        }
        else if(usernameUpdated == 0) { //if username was not updated successfully
            logger.info("Username update failed in UPAM");
            // partition 1 belongs to the authentication listener for rollback
            // send back old username for rollback in auth service
            publisher.updateUsernameFailed(usernames.getNewUsername(), usernames.getOldUsername());
        }
    }

    // rollback this services' username because of update failure in SAT Service
    @KafkaListener(id = "UUFC", topicPartitions = {@TopicPartition(topic = "UpdateUsernameFailed", partitions = {"0"})})
    private void updateUsernameFailed(Payload usernames) {
        logger.info("Received Username rollback message in partition 0: " + usernames.getNewUsername());
        int usernameRolledBack = userInformationRepository.updateUsername(usernames.getOldUsername(), usernames.getNewUsername()); //rollback username
        if(usernameRolledBack == 1) { //if username was rolled back successfully
            logger.info("Username rolled back successfully in UPAM");
        }
        else{
            logger.info("Username rollback failed in UPAM. Retrying");
            usernameRolledBack = userInformationRepository.updateUsername(usernames.getOldUsername(), usernames.getNewUsername()); //rollback username
            logger.info("Retry Result is: " + usernameRolledBack);
        }
    }

    // "remove sessions" failed in SAT Service. Need to readd partner for rollback
    @KafkaListener(id = "DSFC", topicPartitions = {@TopicPartition(topic = "DeleteSessionsFailed", partitions = {"0"})})
    private void deleteSessionsFailed(Payload payload) { //<user_id (owning partner), restored partner username>
        logger.info("Received Message in deleteSessionsFailed partition 0: ");
        UUID partner_id = UUID.randomUUID();
        int feedback = partnersRepository.addPartner(partner_id, payload.getUserId(), payload.getSecondPartner()); //rollback partner
        if(feedback == 1) { //if partner was rolled back successfully
            logger.info("Partner rolled back successfully in UPAM");
        }
        else{
            logger.info("Partner rollback failed in UPAM. Retrying");
            feedback = partnersRepository.addPartner(partner_id, payload.getUserId(), payload.getSecondPartner()); //rollback partner
            logger.info("Retry Result is: " + feedback);
        }
    }

    // add a partner to a user from data given from SAPR Service
    // SAPR should give map with keys user_id, partner, requestor, requestee
    @KafkaListener(id = "APC", topicPartitions = {@TopicPartition(topic = "AddPartner", partitions = {"0"})})
    private void addPartner(Payload payload) {
        logger.info("Received Message in partition 0 addpartner: ");
        UUID partner_id = UUID.randomUUID();
        int feedback = partnersRepository.addPartner(partner_id, payload.getUserId(), payload.getSecondPartner()); //add partner
        if(feedback == 1) { //if partner was added successfully
            logger.info("Partner added successfully in UPAM");
        }
        else if(feedback == 0) { //if partner was not added successfully
            logger.info("Partner add failed in UPAM. Retrying");
            feedback = partnersRepository.addPartner(partner_id, payload.getUserId(), payload.getSecondPartner()); //add partner
            logger.info("Retry Result is: " + feedback);
            if(feedback == 1) {
                logger.info("Partner added successfully in UPAM");
            }
            else {
                logger.info("Partner add failed in UPAM. Sending requester and requested for rollback");
                publisher.addPartnerFailed(payload.getRequester(), payload.getRequested());
            }
        }
    }

    @KafkaListener(id = "CPR", topicPartitions = {@TopicPartition(topic = "CreateUserProfile", partitions = {"0"})})
    private void createProfile(MessageWrapper message) {
        try {
            logger.info("Received Create Profile message in partition 0: " + message.getUsername());
            // create profile
            UserInformationModel model = new UserInformationModel();
            model.setUsername(message.getUsername());
            model.setName("Add your name");
            model.setAge(-1);
            model.setLocation("Add your location");
            model.setExperience("Add your experience");
            model.setAvailability("Add your availability");
            UserInformationModel res = userInformationRepository.save(model);
            if(res == null) {
                logger.info("Create Profile failed in UPAM. Sending message to rollback");
                publisher.createProfileFailed(message);
            }
        } catch (Exception e) {
            logger.info("Exception is: " + e.getMessage());
            logger.info("Create Profile failed in UPAM. Sending message to rollback");
            publisher.createProfileFailed(message);
        }
    }
}