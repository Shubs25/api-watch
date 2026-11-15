package com.apiwatch.analytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NoHealthCheckRecordFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoHealthCheckRecordFoundException(NoHealthCheckRecordFoundException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Map.of("message", e.getMessage()));
    }
}
