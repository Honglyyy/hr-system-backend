package com.ly.hrsystembackend.exception;

import lombok.extern.slf4j.Slf4j;
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
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(error.getObjectName(), error.getDefaultMessage());
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
}
