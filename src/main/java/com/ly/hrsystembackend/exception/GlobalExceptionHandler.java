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
        Map<String, String> errors = getErrors(ex, "Employee not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmployeeIdAlreadyExistException.class)
    public ResponseEntity<?> handleEmployeeIdAlreadyExist(EmployeeIdAlreadyExistException ex) {
        Map<String, String> errors = getErrors(ex, "Employee already exist");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<?> handleLoginFail(LoginFailedException ex) {
        Map<String, String> errors = getErrors(ex, "Login failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(InactiveEmployeeException.class)
    public ResponseEntity<?> handleInactiveEmployee(InactiveEmployeeException ex) {
        Map<String, String> errors = getErrors(ex, "Inactive employee");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(JwtTokenExtractionException.class)
    public ResponseEntity<?> handleJwtTokenExtractionException(JwtTokenExtractionException ex) {
        Map<String, String> errors = getErrors(ex, "JWT Token extraction failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<?> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        Map<String, String> errors = getErrors(ex, "Department not found!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(JobTitleNotFoundException.class)
    public ResponseEntity<?> handleJobTitleNotFoundException(JobTitleNotFoundException ex) {
        Map<String, String> errors = getErrors(ex, "Job title is not found!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(PositionNotFoundException.class)
    public ResponseEntity<?> handlePositionNotFoundException(PositionNotFoundException ex) {
        Map<String, String> errors = getErrors(ex, "Position not found!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    private Map<String, String> getErrors(Exception ex, String message) {
        Map<String, String> errors = new HashMap<>();
        log.warn(message + " " +ex.getMessage());
        errors.put("message", message);
        return errors;
    }
}
