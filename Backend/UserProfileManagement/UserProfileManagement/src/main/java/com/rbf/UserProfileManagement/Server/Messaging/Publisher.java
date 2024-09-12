package com.rbf.UserProfileManagement.Server.Messaging;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Publisher {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final String TOPIC_UPDATE_USERNAME_SAT = "UpdateUsernameSAT";
    private final String TOPIC_UPDATE_USERNAME_FAILED = "UpdateUsernameFailed";
    private final String TOPIC_DELETE_SESSIONS = "DeleteSessions";
    private final String TOPIC_ADD_PARTNER_FAILED = "AddPartnerFailed";
    private final String TOPIC_CREATE_USER_PROFILE_FAILED = "CreateUserProfileFailed";

    @Autowired
    private KafkaTemplate<String, Payload> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, MessageWrapper> kafkaTemplateOther;

    public void updateUsernameSAT(String newUsername, String oldUsername) {
        logger.info("Sending message to update username in SAT: " + newUsername);
        Payload payload = new Payload();
        payload.setOldUsername(oldUsername);
        payload.setNewUsername(newUsername);
        this.kafkaTemplate.send(TOPIC_UPDATE_USERNAME_SAT, 0, "UUSAT", payload); //send message to a topic
    }

    public void updateUsernameFailed(String newUsername, String oldUsername) {
        logger.info("Sending old username for rollback: " + oldUsername); //sending to partitions for asynchronous rollback
        Payload payload = new Payload();
        payload.setOldUsername(oldUsername);
        payload.setNewUsername(newUsername);
        this.kafkaTemplate.send(TOPIC_UPDATE_USERNAME_FAILED, 0, "UUF", payload);
    }

    // after removing partner, send message to SAT to delete all sessions for the partner removed and user in question
    // put in PartnersService
    public void deleteSessions(String firstPartner, String secondPartner, String user_id) {
        logger.info("Sending removed partner username to delete existing sessions: " + firstPartner + " " + secondPartner);
        Payload payload = new Payload();
        payload.setFirstPartner(firstPartner);
        payload.setSecondPartner(secondPartner);
        payload.setUserId(user_id);
        this.kafkaTemplate.send(TOPIC_DELETE_SESSIONS, 0, "DS", payload);
    }

    // could not add partner, send requester and requested for rollback in SAPR
    // put in addPartner in Subscriber
    public void addPartnerFailed(String requester, String requested) {
        logger.info("Sending requestor and requestee for rollback: " + requester + " " + requested);
        Payload payload = new Payload();
        payload.setRequester(requester);
        payload.setRequested(requested);
        this.kafkaTemplate.send(TOPIC_ADD_PARTNER_FAILED, 0, "APF", payload);
    }

    public void createProfileFailed(MessageWrapper message) {
        logger.info("Sending username for rollback: " + message.getUsername());
        this.kafkaTemplateOther.send(TOPIC_CREATE_USER_PROFILE_FAILED, 0, "CPF", message);
    }

}