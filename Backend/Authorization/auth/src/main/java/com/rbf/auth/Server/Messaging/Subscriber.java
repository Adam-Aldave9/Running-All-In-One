package com.rbf.auth.Server.Messaging;

import com.rbf.auth.Server.Repositories.CredentialRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Transactional
public class Subscriber {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private CredentialRepository credentialRepository;

    //update username failed; rollback
    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "UpdateUsernameFailed", partitions = {"1"})})
    private void updateUsernameFailed(HashMap<String, String> usernames) {
        logger.info("Received Username rollback message in partition 1: " + usernames.get("new"));
        int feedback = credentialRepository.updateUsername(usernames.get("old"), usernames.get("new"));
        if(feedback == 1) {
            logger.info("Username rolled back successfully in SAT");
        }
        else {
            logger.info("Username rollback failed in SAT. Retrying");
            feedback = credentialRepository.updateUsername(usernames.get("old"), usernames.get("new"));
            logger.info("Retry Result is: " + feedback);
        }
    }


}