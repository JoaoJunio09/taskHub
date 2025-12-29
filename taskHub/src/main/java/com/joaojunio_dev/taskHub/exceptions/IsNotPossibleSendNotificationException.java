package com.joaojunio_dev.taskHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IsNotPossibleSendNotificationException extends RuntimeException {
    public IsNotPossibleSendNotificationException(String message) {
        super(message);
    }
}
