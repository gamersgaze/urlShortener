package com.apps.shortener.exception;

public class InvalidUrlException extends RuntimeException {
    private String message;

    public InvalidUrlException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
