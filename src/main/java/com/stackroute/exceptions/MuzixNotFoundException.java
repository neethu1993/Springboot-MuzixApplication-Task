package com.stackroute.exceptions;

public class MuzixNotFoundException extends Exception{
    private String message;

    public MuzixNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public MuzixNotFoundException() {
    }
}
