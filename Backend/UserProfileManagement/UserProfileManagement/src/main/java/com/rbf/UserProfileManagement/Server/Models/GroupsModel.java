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
    @Column(updatable = false, nullable = false)
    //@OneToMany
    private UUID group_id;

    //@ManyToOne
    private UUID user_id;
    private String group_name;
}