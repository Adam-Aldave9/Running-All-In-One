package com.rbf.UserProfileManagement.Server.Messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Publisher {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final String TOPIC_UPDATE_USERNAME_SAT = "UpdateUsernameSAT";
    private final String TOPIC_UPDATE_USERNAME_FAILED = "UpdateUsernameFailed";
    private final String TOPIC_DELETE_SESSIONS = "DeleteSessions";
    private final String TOPIC_ADD_PARTNER_FAILED = "AddPartnerFailed";

    @Autowired
    private KafkaTemplate<String, String> kafkaMainTemplate;
    @Autowired
    private KafkaTemplate<String, HashMap<String, String>> kafkaAltTemplate;

    public void updateUsernameSAT(String newUsername) {
        logger.info("Sending message to update username in SAT: " + newUsername);
        this.kafkaMainTemplate.send(TOPIC_UPDATE_USERNAME_SAT, 0, "UUSAT", newUsername); //send message to a topic
    }

    public void updateUsernameFailed(String oldUsername) {
        logger.info("Sending old username for rollback: " + oldUsername); //use sending to partitions for syncronous rollback
        this.kafkaMainTemplate.send(TOPIC_UPDATE_USERNAME_FAILED, 0, "UUF", oldUsername);
    }

    public void deleteSessions(String message) { //need to look at this and database design
        logger.info("Sending Message: " + message);
        this.kafkaMainTemplate.send(TOPIC_DELETE_SESSIONS, 0, "DS", message);
    }

    public void addPartnerFailed(String requestor, String requestee) {
        logger.info("Sending requestor and requestee for rollback: " + requestor + " " + requestee);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("requestor", requestor);
        map.put("requestee", requestee);
        this.kafkaAltTemplate.send(TOPIC_ADD_PARTNER_FAILED, 0, "APF", map);
    }


}