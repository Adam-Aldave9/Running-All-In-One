package com.rbf.UserProfileManagement.Server.Exceptions;

import java.util.UUID;

public class PartnerNotFoundException extends RuntimeException{
    public PartnerNotFoundException(Integer id) {
        super("Could not find partner " + id);
    }

    public PartnerNotFoundException(UUID id) {
        super("Could not find partner " + id);
    }
}