package com.ly.hrsystembackend.exception;

public class InactiveEmployeeException extends RuntimeException {
    public InactiveEmployeeException(String message) {
        super(message);
    }
}
