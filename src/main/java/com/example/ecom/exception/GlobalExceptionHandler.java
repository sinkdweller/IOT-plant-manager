package com.example.ecom.exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeError(RuntimeException ex) {
        System.err.println("Error caught: " + ex.getMessage());
        
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}