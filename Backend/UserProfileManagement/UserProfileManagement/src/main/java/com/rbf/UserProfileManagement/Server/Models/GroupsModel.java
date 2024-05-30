package com.rbf.UserProfileManagement.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Groups")
@Getter @Setter @NoArgsConstructor
public class GroupsModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "group_id", updatable = false, nullable = false)
    //@OneToMany
    private UUID groupId;

    //@ManyToOne
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "group_name")
    private String groupName;
}