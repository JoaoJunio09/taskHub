package com.joaojunio_dev.taskHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class B2InvalidFileFormatException extends RuntimeException {
    public B2InvalidFileFormatException(String message) {
        super(message);
    }
}
