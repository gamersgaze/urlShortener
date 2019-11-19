package com.apps.shortener.exception;

public class KeyNotFoundException extends RuntimeException {
    private String message;

    public KeyNotFoundException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
