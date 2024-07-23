package com.rbf.Scheduling.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Sessions")
@Getter @Setter @NoArgsConstructor
public class SessionsModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "session_id", updatable = false, nullable = false)
    private UUID sessionId;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "location")
    private String location;
}