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
    @Column(name = "user_id", updatable = false, nullable = false)
    //@OneToMany
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "location")
    private String location;

    @Column(name = "experience")
    private String experience;

    @Column(name = "availability")
    private String availability;

}