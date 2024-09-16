package com.rbf.UserProfileManagement.Server.Exceptions;

import java.util.UUID;

public class UserInformationNotFoundException extends RuntimeException{
    public UserInformationNotFoundException(Integer id) {
        super("Could not find user " + id);
    }

    public UserInformationNotFoundException(UUID id) {
        super("Could not find user " + id);
    }
}