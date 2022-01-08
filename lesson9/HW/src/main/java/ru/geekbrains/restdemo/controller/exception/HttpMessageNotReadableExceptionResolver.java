package ru.geekbrains.restdemo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class HttpMessageNotReadableExceptionResolver {
    @ExceptionHandler
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) throws IOException {
        return new ResponseEntity<>("Parse error", HttpStatus.BAD_REQUEST);
    }
}
