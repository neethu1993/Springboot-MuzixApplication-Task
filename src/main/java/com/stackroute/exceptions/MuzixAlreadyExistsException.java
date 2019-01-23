/**
 * Custom Exception  for muzix already exists exception created here
 */
package com.stackroute.exceptions;

public class MuzixAlreadyExistsException extends Exception {

    //Message variable
    private String message;

    //Default Constructor
    public MuzixAlreadyExistsException() {
    }

    //Parameterized constructor
    public MuzixAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
