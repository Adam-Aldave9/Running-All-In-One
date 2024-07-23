package com.rbf.Scheduling.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Group Sessions")
@Getter @Setter @NoArgsConstructor
public class GroupSessionsModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "group_sessions_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "location")
    private String location;

}