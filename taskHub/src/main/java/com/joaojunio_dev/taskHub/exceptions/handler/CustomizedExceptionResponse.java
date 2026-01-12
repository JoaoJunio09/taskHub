package com.joaojunio_dev.taskHub.exceptions.handler;

import com.joaojunio_dev.taskHub.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class CustomizedExceptionResponse {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> allExceptionsHandler(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(exceptionResponse);
    }

    @ExceptionHandler(ObjectIsNullException.class)
    public ResponseEntity<ExceptionResponse> objectIsNullExceptionHandler(ObjectIsNullException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<ExceptionResponse> invalidJwtAuthenticationExceptionHandler(InvalidJwtAuthenticationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidTypeOfDateException.class)
    public ResponseEntity<ExceptionResponse> invalidTypeOfDateExceptionHandler(InvalidTypeOfDateException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(true),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IsNotPossibleSendNotificationException.class)
    public ResponseEntity<ExceptionResponse> notPossibleSendNotificationExceptionHandler(IsNotPossibleSendNotificationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(true),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ExceptionResponse> fileStorageExceptionHandler(FileStorageException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(true),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ExceptionResponse> b2ExceptionHandler(FileStorageException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(true),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}