package com.ly.hrsystembackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {
            fieldErrors.putIfAbsent(error.getField(), error.getDefaultMessage());
        });
        return badRequest("Validation failed", fieldErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return badRequest("Request body is missing or malformed.", Map.of());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return badRequest(messageOrDefault(ex.getMessage(), "Invalid request."), Map.of());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        return badRequest("Missing multipart part: " + ex.getRequestPartName(), Map.of());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handleMultipartException(MultipartException ex) {
        return badRequest("Invalid multipart request.", Map.of());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Current password is incorrect."));
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

    private ResponseEntity<Map<String, Object>> badRequest(String message, Map<String, String> fieldErrors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", message);
        if (!fieldErrors.isEmpty()) {
            body.put("errors", fieldErrors);
        }
        return ResponseEntity.badRequest().body(body);
    }

    private String messageOrDefault(String message, String fallback) {
        return message == null || message.isBlank() ? fallback : message;
    }
}
