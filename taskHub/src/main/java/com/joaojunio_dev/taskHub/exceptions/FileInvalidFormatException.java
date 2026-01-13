package com.joaojunio_dev.taskHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class FileInvalidFormatException extends RuntimeException {
    public FileInvalidFormatException(String message) {
        super(message);
    }
}
