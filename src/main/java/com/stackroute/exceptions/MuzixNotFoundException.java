package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class MuzixNotFoundException extends Exception{
    private String message;

    public MuzixNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public MuzixNotFoundException() {
    }
}
