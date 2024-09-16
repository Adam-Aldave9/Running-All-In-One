package com.rbf.Scheduling.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Session Participants")
@Getter @Setter @NoArgsConstructor
public class SessionParticipantsModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "participants_id", updatable = false, nullable = false)
    private UUID participantsId;

    @Column(name = "partner_one")
    private String partnerOne;

    @Column(name = "partner_two")
    private String partnerTwo;

    @Column(name = "session_id")
    private UUID sessionId;

}