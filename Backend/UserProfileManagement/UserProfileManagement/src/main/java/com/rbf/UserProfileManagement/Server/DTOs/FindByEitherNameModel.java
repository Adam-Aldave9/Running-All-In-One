package com.rbf.UserProfileManagement.Server.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class FindByEitherNameModel {
    private UUID userId;
    private String username;
    private String name;
    private String location;
    private String availability;
}