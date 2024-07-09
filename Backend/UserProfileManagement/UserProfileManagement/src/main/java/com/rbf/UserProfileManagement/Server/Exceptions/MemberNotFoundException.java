package com.rbf.UserProfileManagement.Server.Exceptions;

import java.util.UUID;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(Integer id) {
        super("Could not find employee " + id);
    }

    public MemberNotFoundException(UUID id) {
        super("Could not find employee " + id);
    }
}