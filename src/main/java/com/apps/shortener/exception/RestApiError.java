package com.apps.shortener.exception;

import java.io.Serializable;

public class RestApiError implements Serializable {
    private static final long serialVersionUID = 1L;
    private int status;
    private String errorMessage;


    public RestApiError(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
