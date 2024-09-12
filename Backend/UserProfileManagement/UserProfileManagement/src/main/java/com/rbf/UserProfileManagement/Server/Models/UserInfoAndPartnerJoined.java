package com.rbf.UserProfileManagement.Server.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor
public class UserInfoAndPartnerJoined {
    private String partnerName;
    private UUID partnerId;
}