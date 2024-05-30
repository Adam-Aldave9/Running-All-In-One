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
    private Integer id;

    //@ManyToOne
    private UUID user_id;
    //@ManyToOne
    private UUID group_id;
}