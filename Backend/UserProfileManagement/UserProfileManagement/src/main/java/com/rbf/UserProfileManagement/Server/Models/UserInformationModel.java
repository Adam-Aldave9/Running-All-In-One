package com.rbf.UserProfileManagement.Server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "User Information")
@Getter @Setter @NoArgsConstructor
public class UserInformationModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    //@OneToMany
    private UUID user_id;

    private String username;
    private String name;
    private Integer age;
    private String location;
    private String experience;
    private String availability;

}