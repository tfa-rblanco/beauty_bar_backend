package com.beautybar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<com.beautybar.dto.ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(com.beautybar.dto.ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<com.beautybar.dto.ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(com.beautybar.dto.ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<com.beautybar.dto.ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(com.beautybar.dto.ApiResponse.error("Validation failed"));
    }

    @ExceptionHandler(KeycloakException.class)
    public ResponseEntity<com.beautybar.dto.ApiResponse<Object>> handleKeycloakException(KeycloakException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(com.beautybar.dto.ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.beautybar.dto.ApiResponse<Object>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(com.beautybar.dto.ApiResponse.error("An unexpected error occurred"));
    }

    // Error response classes
    public static class ErrorResponse {
        private int status;
        private String message;
        private LocalDateTime timestamp;

        public ErrorResponse(int status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        // Getters
        public int getStatus() { return status; }
        public String getMessage() { return message; }
        public LocalDateTime getTimestamp() { return timestamp; }
    }

    public static class ValidationErrorResponse extends ErrorResponse {
        private Map<String, String> fieldErrors;

        public ValidationErrorResponse(int status, String message, LocalDateTime timestamp, Map<String, String> fieldErrors) {
            super(status, message, timestamp);
            this.fieldErrors = fieldErrors;
        }

        public Map<String, String> getFieldErrors() { return fieldErrors; }
    }
}