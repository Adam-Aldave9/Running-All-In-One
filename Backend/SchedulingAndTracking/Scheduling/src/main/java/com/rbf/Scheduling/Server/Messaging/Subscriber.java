package com.rbf.Scheduling.Server.Messaging;

import com.rbf.Scheduling.Server.Repositories.GroupParticipantsRepository;
import com.rbf.Scheduling.Server.Repositories.SessionParticipantsRepository;

import com.rbf.common.KafkaPayloads.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class Subscriber {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);
    @Autowired
    private GroupParticipantsRepository groupParticipantsRepository;
    @Autowired
    private SessionParticipantsRepository sessionParticipantsRepository;
    @Autowired
    Publisher publisher;

    //update username SAT
    @KafkaListener(id = "UUC", topicPartitions = {@TopicPartition(topic = "UpdateUsernameSAT", partitions = {"0"})})
    private void updateUsernamesSAT(Payload usernames) {
        logger.info("Update Username Received Message in partition 0: " + usernames.getNewUsername());
        int gpUpdated = groupParticipantsRepository.updateUsername(usernames.getNewUsername(), usernames.getOldUsername()); //query to update username
        int spUpdated = sessionParticipantsRepository.updateUsername(usernames.getNewUsername(), usernames.getOldUsername());
        if(gpUpdated + spUpdated >= 1) { //if username was updated successfully
            logger.info("Username updated successfully in UPAM");
        }
        else if(gpUpdated + spUpdated == 0) { //if username was not updated successfully
            logger.info("Username update failed in UPAM");
            // partition 1 belongs to the authentication listener for rollback
            // send back old username for rollback in UPAM service
            publisher.updateUsernameFailed(usernames.getNewUsername(), usernames.getOldUsername());
        }
    }

    //delete sessions
    @KafkaListener(id = "DSC", topicPartitions = {@TopicPartition(topic = "DeleteSessions", partitions = {"0"})})
    private void deleteSessions(Payload data){
        logger.info("Received delete sessions message in partition 0: " + data.getFirstPartner() + " " + data.getSecondPartner());
        int feedback = sessionParticipantsRepository.deleteSessions(data.getFirstPartner(), data.getSecondPartner()); //database deletion method
        if(feedback == 0){
            logger.info("Unable to delete sessions in SAT");
            publisher.deleteSessionsFailed(data.getUserId(), data.getSecondPartner());
        }
        logger.info("Deleted sessions successfully in SAT");
        logger.info("No sessions to delete in SAT");
    }

}