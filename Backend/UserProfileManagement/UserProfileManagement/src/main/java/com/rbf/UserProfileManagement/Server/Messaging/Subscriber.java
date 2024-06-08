package com.rbf.UserProfileManagement.Server.Messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class Subscriber {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "UpdateUsernameUPAM", partitions = {"0"})})
    private void updateUsername(String message) {
        logger.info("Received Message in 0: " + message);
    }

    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "UpdateUsernameFailed", partitions = {"0"})})
    private void updateUsernameFailed(String message) {
        logger.info("Received Message in 0: " + message);
    }

    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "DeleteSessionsFailed", partitions = {"0"})})
    private void deleteSessionsFailed(String message) {
        logger.info("Received Message in 0: " + message);
    }

    @KafkaListener(id = "group_id", topicPartitions = {@TopicPartition(topic = "AddPartner", partitions = {"0"})})
    private void addPartner(String message) {
        logger.info("Received Message in 0: " + message);
    }

}