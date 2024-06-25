package com.rbf.UserProfileManagement.Server.Messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

//MUST SEND ALL RELEVENT INFO USING MAPS
@Service
public class Publisher {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final String TOPIC_UPDATE_USERNAME_SAT = "UpdateUsernameSAT";
    private final String TOPIC_UPDATE_USERNAME_FAILED = "UpdateUsernameFailed";
    private final String TOPIC_DELETE_SESSIONS = "DeleteSessions";
    private final String TOPIC_ADD_PARTNER_FAILED = "AddPartnerFailed";

    @Autowired
    private KafkaTemplate<String, HashMap<String, String>> kafkaTemplate;

    public void updateUsernameSAT(String newUsername, String oldUsername) {
        logger.info("Sending message to update username in SAT: " + newUsername);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("oldUsername", oldUsername);
        data.put("newUsername", newUsername);
        this.kafkaTemplate.send(TOPIC_UPDATE_USERNAME_SAT, 0, "UUSAT", data); //send message to a topic
    }

    public void updateUsernameFailed(String newUsername, String oldUsername) {
        logger.info("Sending old username for rollback: " + oldUsername); //use sending to partitions for asynchronous rollback
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("oldUsername", oldUsername);
        data.put("newUsername", newUsername);
        this.kafkaTemplate.send(TOPIC_UPDATE_USERNAME_FAILED, 0, "UUF", data);
    }

    // after removing partner, send message to SAT to delete all sessions for the partner removed and user in question
    // put in PartnersService
    public void deleteSessions(String firstPartner, String secondPartner) {
        logger.info("Sending removed partner username to delete existing sessions: " + firstPartner + " " + secondPartner);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("firstpartner", firstPartner);
        data.put("secondpartner", secondPartner);
        this.kafkaTemplate.send(TOPIC_DELETE_SESSIONS, 0, "DS", data);
    }

    // could not add partner, send requestor and requestee for rollback in SAPR
    // put in addPartner in Subscriber
    public void addPartnerFailed(String requestor, String requestee) {
        logger.info("Sending requestor and requestee for rollback: " + requestor + " " + requestee);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("requestor", requestor);
        data.put("requestee", requestee);
        this.kafkaTemplate.send(TOPIC_ADD_PARTNER_FAILED, 0, "APF", data);
    }
}