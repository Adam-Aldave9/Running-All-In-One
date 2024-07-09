package com.rbf.Scheduling.Server.Exceptions;

import java.util.UUID;

public class GroupSessionNotFoundException extends RuntimeException{
    public GroupSessionNotFoundException(Integer id) {
        super("Could not find group session " + id);
    }

    public GroupSessionNotFoundException(UUID id) {
        super("Could not find group session " + id);
    }
}