package com.rbf.auth.Server.Exceptions;

import java.util.UUID;

public class CredentialNotFoundException extends RuntimeException{
    public CredentialNotFoundException(UUID id) {
        super("Could not find credential " + id);
    }
}