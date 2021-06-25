package com.example.automatapplication.exception;

import org.springframework.http.HttpStatus;

/**
 * @author created by cengizhan on 24.06.2021
 */
public class AutomatException extends RuntimeException {
    private HttpStatus httpStatus;

    public AutomatException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
