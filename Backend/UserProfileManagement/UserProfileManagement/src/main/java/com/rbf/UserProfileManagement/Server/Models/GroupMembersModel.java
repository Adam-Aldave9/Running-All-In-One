package com.rbf.UserProfileManagement.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Group Members")
@Getter @Setter @NoArgsConstructor
public class GroupMembersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "members_id", updatable = false, nullable = false)
    private Integer id;

    //@ManyToOne
    @Column(name = "user_id")
    private UUID userId;

    //@ManyToOne
    @Column(name = "group_id")
    private UUID groupId;
}