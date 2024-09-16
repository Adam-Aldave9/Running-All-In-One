package com.rbf.UserProfileManagement.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Partners")
@Getter @Setter @NoArgsConstructor
public class PartnersModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "partner_id", updatable = false, nullable = false)
    private UUID id;

    //@ManyToOne
    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "partner_name")
    private String partnerName;

    public PartnersModel(UUID userID, String partnerName) {
        this.userID = userID;
        this.partnerName = partnerName;
    }
}