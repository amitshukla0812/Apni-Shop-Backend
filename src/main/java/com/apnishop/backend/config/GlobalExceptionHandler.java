package com.apnishop.backend.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Temporary/diagnostic: returns the real exception message in the JSON
// response instead of Spring's generic blank 500 error page, so we can see
// exactly what's failing (e.g. during migration).
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAny(Exception ex) {
        ex.printStackTrace(); // still prints full stack trace in the console

        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getClass().getName());
        body.put("message", String.valueOf(ex.getMessage()));

        Throwable cause = ex.getCause();
        if (cause != null) {
            body.put("cause", cause.getClass().getName() + ": " + cause.getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
