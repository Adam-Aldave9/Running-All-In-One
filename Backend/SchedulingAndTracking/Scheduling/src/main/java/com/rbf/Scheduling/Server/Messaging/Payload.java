package com.rbf.Scheduling.Server.Messaging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payload {
    private String oldUsername;
    private String newUsername;
    private String firstPartner;
    private String secondPartner;
    private String userId;
    private String requester;
    private String requested;
}