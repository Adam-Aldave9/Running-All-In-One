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
    @Column(updatable = false, nullable = false)
    private UUID id;

    //@ManyToOne
    private UUID userID;
    private String partnerName;

    public PartnersModel(UUID userID, String partnerName) {
        this.userID = userID;
        this.partnerName = partnerName;
    }
}