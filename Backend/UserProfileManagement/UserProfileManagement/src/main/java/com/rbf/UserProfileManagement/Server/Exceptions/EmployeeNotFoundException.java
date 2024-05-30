package com.rbf.UserProfileManagement.Server.Exceptions;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Integer id) {
        super("Could not find employee " + id);
    }

    public EmployeeNotFoundException(UUID id) {
        super("Could not find employee " + id);
    }
}