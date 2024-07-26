package com.rbf.Scheduling.Server.Messaging;

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

    @Autowired
    private KafkaTemplate<String, HashMap<String, String>> kafkaTemplate;

    public void updateUsernameFailed(String newUsername, String oldUsername) {
        logger.info("Sending old username for rollback: " + oldUsername); //use sending to partitions for asynchronous rollback
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("old", oldUsername);
        data.put("new", newUsername);
        this.kafkaTemplate.send("UpdateUsernameFailed", 0, "UUF", data);
    }

    public void deleteSessionsFailed(String user_id, String secondPartner) {
        logger.info("Sending removed partner username to delete existing sessions: " + secondPartner);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id); //user_id is the user who removed the partner
        data.put("secondpartner", secondPartner);
        this.kafkaTemplate.send("DeleteSessionsFailed", 0, "DSF", data);
    }

    public void updateUsernameSAPR(String newUsername, String oldUsername){
        logger.info("Sending message to update username in SAPR: " + newUsername);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("old", oldUsername);
        data.put("new", newUsername);
        this.kafkaTemplate.send("UpdateUsernameSAPR", 0, "UUSAPR", data); //send message to a topic
    }

}