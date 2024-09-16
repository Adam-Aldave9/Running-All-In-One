package com.rbf.auth.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "History")
@Getter @Setter @NoArgsConstructor
public class HistoryModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "history_id", updatable = false, nullable = false)
    private UUID historyId;

    @Column(name = "credential_id")
    private UUID credentialId;

    @Column(name = "last_login")
    private LocalDate lastLogin;
}