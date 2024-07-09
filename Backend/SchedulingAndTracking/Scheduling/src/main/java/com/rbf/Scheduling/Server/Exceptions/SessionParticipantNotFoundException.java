package com.rbf.Scheduling.Server.Exceptions;

import java.util.UUID;

public class SessionParticipantNotFoundException extends RuntimeException{
    public SessionParticipantNotFoundException(Integer id) {
        super("Could not find session participant " + id);
    }

    public SessionParticipantNotFoundException(UUID id) {
        super("Could not find session participant " + id);
    }
}