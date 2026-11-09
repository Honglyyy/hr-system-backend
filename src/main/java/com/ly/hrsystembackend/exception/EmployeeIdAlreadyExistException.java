package com.ly.hrsystembackend.exception;

public class EmployeeIdAlreadyExistException extends RuntimeException {
    public EmployeeIdAlreadyExistException(String message) {
        super(message);
    }
}
