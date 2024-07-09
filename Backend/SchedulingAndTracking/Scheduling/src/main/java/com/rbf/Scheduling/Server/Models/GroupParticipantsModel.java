package com.rbf.Scheduling.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Group Participants")
@Getter @Setter @NoArgsConstructor
public class GroupParticipantsModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "group_sessions_id")
    private UUID groupSessionsId;

    @Column(name = "participant_name")
    private String participantName;
}