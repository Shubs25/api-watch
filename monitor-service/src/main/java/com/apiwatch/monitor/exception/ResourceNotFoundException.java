package com.apiwatch.monitor.exception;

public class ResourceNotFoundException extends RuntimeException  {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
