package com.rbf.Scheduling.Server.Messaging;

import com.rbf.Scheduling.Server.Repositories.GroupParticipantsRepository;
import com.rbf.Scheduling.Server.Repositories.SessionParticipantsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class Subscriber {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);
    @Autowired
    private GroupParticipantsRepository groupParticipantsRepository;
    @Autowired
    private SessionParticipantsRepository sessionParticipantsRepository;
    @Autowired
    Publisher publisher;

    //update username SAT
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "UpdateUsernameSAT", partitions = {"0"})})
    private void updateUsernamesSAT(Map<String, String> usernames) {
        logger.info("Update Username Received Message in partition 0: " + usernames.get("new"));
        int gpUpdated = groupParticipantsRepository.updateUsername(usernames.get("new"), usernames.get("old")); //query to update username
        int spUpdated = sessionParticipantsRepository.updateUsername(usernames.get("new"), usernames.get("old"));
        if(gpUpdated + spUpdated >= 1) { //if username was updated successfully
            logger.info("Username updated successfully in UPAM");
            publisher.updateUsernameSAPR(usernames.get("new"), usernames.get("old"));
        }
        else if(gpUpdated + spUpdated == 0) { //if username was not updated successfully
            logger.info("Username update failed in UPAM");
            // partition 1 belongs to the authentication listener for rollback
            // send back old username for rollback in UPAM service
            publisher.updateUsernameFailed(usernames.get("new"), usernames.get("old"));
        }
    }

    //update username failed
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "UpdateUsernameFailed", partitions = {"2"})})
    private void updateUsernameFailed(Map<String, String> usernames) {
        logger.info("Received Username rollback message in partition 2: " + usernames.get("new"));
        int gpUpdated = groupParticipantsRepository.updateUsername(usernames.get("old"), usernames.get("new")); //query to update username
        int spUpdated = sessionParticipantsRepository.updateUsername(usernames.get("old"), usernames.get("new"));
        if(gpUpdated + spUpdated >= 1)  { //if username was rolled back successfully
            logger.info("Username rolled back successfully in UPAM");
        }
        else{
            logger.info("Username rollback failed in UPAM. Retrying");
            gpUpdated = groupParticipantsRepository.updateUsername(usernames.get("old"), usernames.get("new")); //query to update username
            spUpdated = sessionParticipantsRepository.updateUsername(usernames.get("old"), usernames.get("new"));
            logger.info("Retry Result is: " + gpUpdated);
            logger.info("Retry Result is: " + spUpdated);
        }
    }

    //delete sessions
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "DeleteSessions", partitions = {"0"})})
    private void deleteSessions(HashMap<String, String> data){
        logger.info("Received delete sessions message in partition 0: " + data.get("firstpartner") + " " + data.get("secondpartner"));
        int feedback = sessionParticipantsRepository.deleteSessions(data.get("firstpartner"), data.get("secondpartner")); //database deletion method
        if(feedback == 0){
            logger.info("Unable to delete sessions in SAT");
            publisher.deleteSessionsFailed(data.get("user_id"), data.get("secondpartner"));
        }
        logger.info("Deleted sessions successfully in SAT");
        logger.info("No sessions to delete in SAT");
    }

}