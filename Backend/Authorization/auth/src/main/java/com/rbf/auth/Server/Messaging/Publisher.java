package com.rbf.auth.Server.Messaging;

import com.rbf.common.KafkaPayloads.MessageWrapper;
import com.rbf.common.KafkaPayloads.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Publisher {
    private final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final String TOPIC_UPDATE_USERNAME_UPAM = "UpdateUsernameUPAM";
    private final String TOPIC_CREATE_PROFILE_RECORD = "CreateUserProfile";

    @Autowired
    private KafkaTemplate<String, Payload> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, MessageWrapper> kafkaTemplateOther;

    public void updateUsernameUPAM(String newUsername, String oldUsername) {
        logger.info("Sending message to update username in UPAM: " + newUsername);
        Payload payload = new Payload();
        payload.setOldUsername(oldUsername);
        payload.setNewUsername(newUsername);
        this.kafkaTemplate.send(TOPIC_UPDATE_USERNAME_UPAM, 0, "UUUP", payload); //send message to a topic
    }

    public void createProfile(String username){
        logger.info("Sending message to create profile in UPAM: " + username);
        MessageWrapper message = new MessageWrapper();
        message.setUsername(username);
        this.kafkaTemplateOther.send(TOPIC_CREATE_PROFILE_RECORD, 0, "CPR", message); //send message to a topic
    }

}