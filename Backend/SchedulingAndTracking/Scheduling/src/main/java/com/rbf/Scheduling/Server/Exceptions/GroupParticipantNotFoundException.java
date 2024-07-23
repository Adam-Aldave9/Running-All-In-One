package com.rbf.Scheduling.Server.Exceptions;

import java.util.UUID;

public class GroupParticipantNotFoundException extends RuntimeException {
    public GroupParticipantNotFoundException(Integer id) {
        super("Could not find group participant " + id);
    }

    public GroupParticipantNotFoundException(UUID id) {
        super("Could not find group participant " + id);
    }
}