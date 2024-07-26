package com.rbf.auth.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Credentials")
@Getter @Setter @NoArgsConstructor
public class CredentialsModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "credential_id", updatable = false, nullable = false)
    private UUID credentialId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}