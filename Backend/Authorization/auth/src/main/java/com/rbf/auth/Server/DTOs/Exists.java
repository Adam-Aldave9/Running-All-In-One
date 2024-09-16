package com.rbf.auth.Server.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Exists {
    private boolean exists;
    public Exists(boolean exists) {
        this.exists = exists;
    }
}