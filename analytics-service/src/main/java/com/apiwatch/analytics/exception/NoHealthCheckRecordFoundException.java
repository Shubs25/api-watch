package com.apiwatch.analytics.exception;

public class NoHealthCheckRecordFoundException extends RuntimeException {
    public NoHealthCheckRecordFoundException(String message) {
        super(message);
    }
}
