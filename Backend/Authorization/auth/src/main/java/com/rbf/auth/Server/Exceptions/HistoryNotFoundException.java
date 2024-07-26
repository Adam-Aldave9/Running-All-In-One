package com.rbf.auth.Server.Exceptions;

import java.util.UUID;

public class HistoryNotFoundException extends RuntimeException{
    public HistoryNotFoundException(Integer id) {
        super("Could not find history " + id);
    }

    public HistoryNotFoundException(UUID id) {
        super("Could not find history " + id);
    }
}