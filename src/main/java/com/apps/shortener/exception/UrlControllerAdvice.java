package com.apps.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Controller advice avoids showing error trace in the response
 * and present customised and unified exception code and message
 */

@RestControllerAdvice
public class UrlControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<RestApiError> handleInvalidUrlException(InvalidUrlException e) {
        RestApiError restApiException = new RestApiError(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restApiException);
    }

    @ExceptionHandler(KeyNotFoundException.class)
    public ResponseEntity<RestApiError> handleKeyNotFoundException(KeyNotFoundException e) {
        RestApiError restApiException = new RestApiError(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restApiException);
    }
}
