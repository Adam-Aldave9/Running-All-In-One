package com.rbf.auth.Server.Messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Publisher {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final String TOPIC_UPDATE_USERNAME_UPAM = "UpdateUsernameUPAM";

    @Autowired
    private KafkaTemplate<String, Payload> kafkaTemplate;

    public void updateUsernameUPAM(String newUsername, String oldUsername) {
        logger.info("Sending message to update username in UPAM: " + newUsername);
        Payload payload = new Payload();
        payload.setOldUsername(oldUsername);
        payload.setNewUsername(newUsername);
        this.kafkaTemplate.send(TOPIC_UPDATE_USERNAME_UPAM, 0, "UUUP", payload); //send message to a topic
    }


}