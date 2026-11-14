package com.ly.hrsystembackend.exception;

public class JwtTokenExtractionException extends RuntimeException {
    public JwtTokenExtractionException(String message) {
        super(message);
    }
}
