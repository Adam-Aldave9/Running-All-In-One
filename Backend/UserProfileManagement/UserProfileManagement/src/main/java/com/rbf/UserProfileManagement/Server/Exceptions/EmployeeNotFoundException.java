package com.rbf.UserProfileManagement.Server.Exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Integer id) {
        super("Could not find employee " + id);
    }
}