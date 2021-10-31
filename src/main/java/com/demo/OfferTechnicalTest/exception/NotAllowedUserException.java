package com.demo.OfferTechnicalTest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotAllowedUserException extends Exception {

    public NotAllowedUserException(String message) {
        super(message);
    }

    public String getMessage(String message) {
        return message;
    }
}
