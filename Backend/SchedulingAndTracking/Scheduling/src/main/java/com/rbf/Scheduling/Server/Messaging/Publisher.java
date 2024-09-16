package com.rbf.Scheduling.Server.Messaging;

import com.rbf.common.KafkaPayloads.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Publisher {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private KafkaTemplate<String, Payload> kafkaTemplate;

    public void updateUsernameFailed(String newUsername, String oldUsername) {
        logger.info("Sending old username for rollback: " + oldUsername); //sending to partitions for asynchronous rollback
        Payload payload = new Payload();
        payload.setOldUsername(oldUsername);
        payload.setNewUsername(newUsername);
        this.kafkaTemplate.send("UpdateUsernameFailed", 0, "UUF", payload);
    }

    public void deleteSessionsFailed(String userId, String secondPartner) {
        logger.info("Sending removed partner username to delete existing sessions: " + secondPartner);
        Payload payload = new Payload();
        payload.setUserId(userId);
        payload.setSecondPartner(secondPartner);
        this.kafkaTemplate.send("DeleteSessionsFailed", 0, "DSF", payload);
    }

}