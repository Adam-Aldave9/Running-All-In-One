package com.rbf.auth.Server.Messaging;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Transactional
public class Publisher {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final String TOPIC_UPDATE_USERNAME_UPAM = "UpdateUsernameUPAM";

    @Autowired
    private KafkaTemplate<String, HashMap<String, String>> kafkaTemplate;

    public void updateUsernameUPAM(String newUsername, String oldUsername) {
        logger.info("Sending message to update username in UPAM: " + newUsername);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("old", oldUsername);
        data.put("new", newUsername);
        this.kafkaTemplate.send(TOPIC_UPDATE_USERNAME_UPAM, 0, "UUUP", data); //send message to a topic
    }


}