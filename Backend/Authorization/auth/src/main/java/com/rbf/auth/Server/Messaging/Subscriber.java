package com.rbf.auth.Server.Messaging;

import com.rbf.auth.Server.Repositories.CredentialRepository;
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
    private CredentialRepository credentialRepository;

    //update username failed; rollback
    @KafkaListener(id = "UUFC", topicPartitions = {@TopicPartition(topic = "UpdateUsernameFailed", partitions = {"1"})})
    private void updateUsernameFailed(Payload usernames) {
        logger.info("Received Username rollback message in partition 1: " + usernames.getNewUsername());
        int feedback = credentialRepository.updateUsername(usernames.getOldUsername(), usernames.getNewUsername());
        if(feedback == 1) {
            logger.info("Username rolled back successfully in SAT");
        }
        else {
            logger.info("Username rollback failed in SAT. Retrying");
            feedback = credentialRepository.updateUsername(usernames.getOldUsername(), usernames.getNewUsername());
            logger.info("Retry Result is: " + feedback);
        }
    }

    @KafkaListener(id = "CPF", topicPartitions = {@TopicPartition(topic = "CreateUserProfileFailed", partitions = {"0"})})
    private void createProfileFailed(MessageWrapper message) {
        logger.info("Received Create Profile rollback message in partition 0: " + message.getUsername());
        credentialRepository.deleteByUsername(message.getUsername());
    }

}