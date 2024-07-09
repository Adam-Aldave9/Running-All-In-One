package com.rbf.UserProfileManagement.Server.Exceptions;

import java.util.UUID;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(Integer id) {
        super("Could not find group " + id);
    }

    public GroupNotFoundException(UUID id) {
        super("Could not find group " + id);
    }
}