package com.joaojunio_dev.taskHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class B2Exception extends RuntimeException {
    public B2Exception(String message) {
        super(message);
    }
}
