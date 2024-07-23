package com.rbf.Scheduling.Server.Exceptions;

import java.util.UUID;

public class SessionNotFoundException extends RuntimeException{
    public SessionNotFoundException(Integer id) {
        super("Could not find session " + id);
    }
    public SessionNotFoundException(UUID id) {
        super("Could not find session " + id);
    }
}