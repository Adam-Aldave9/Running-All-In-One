package com.rbf.Scheduling.Server.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter @AllArgsConstructor
public class SessionJoinModel {
    private String partnerOne;
    private String partnerTwo;
    private String date;
    private String location;
    private String time;
    private UUID sessionId;
}