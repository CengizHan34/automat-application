package com.example.automatapplication.exception.handler;

import com.example.automatapplication.exception.AutomatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author created by cengizhan on 24.06.2021
 */
@ControllerAdvice
public class AutomatExceptionHandler {

    @ExceptionHandler(value = {AutomatException.class})
    public ResponseEntity AutomatExceptionHandler(AutomatException automatException) {
        System.out.println("");
        return ResponseEntity.status(automatException.getHttpStatus()).body(automatException.getMessage());
    }
}
