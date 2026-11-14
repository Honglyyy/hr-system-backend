package com.ly.hrsystembackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<String, String>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        log.warn("Employee not found {}",ex.getMessage());
        errors.put("message", "Employee not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmployeeIdAlreadyExistException.class)
    public ResponseEntity<?> handleEmployeeIdAlreadyExist(EmployeeIdAlreadyExistException ex) {
        Map<String, String> errors = new HashMap<>();
        log.warn("Employee already exist {}",ex.getMessage());
        errors.put("message", "Employee already exist");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<?> handleLoginFail(LoginFailedException ex) {
        Map<String, String> errors = new HashMap<>();
        log.warn("Login fail!! {}",ex.getMessage());
        errors.put("message", "The login fail");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(InactiveEmployeeException.class)
    public ResponseEntity<?> handleInactiveEmployee(InactiveEmployeeException ex) {
        Map<String, String> errors = new HashMap<>();
        log.warn("Employee is not active!! {}",ex.getMessage());
        errors.put("message", "Employee is not active");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(JwtTokenExtractionException.class)
    public ResponseEntity<?> handleJwtTokenExtractionException(JwtTokenExtractionException ex) {
        Map<String, String> errors = new HashMap<>();
        log.warn("Token extraction fail!! {}",ex.getMessage());
        errors.put("message", "Token extraction fail");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
}
