/**
 * Global Exception controller to handle all the raised exceptions
 */
package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {

    //Exception handler to handle Muzix not found exceptions
    @ExceptionHandler(MuzixNotFoundException.class)
    public ResponseEntity handleMuzixNotFoundException(final MuzixNotFoundException e) {
        return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    //Exception handler to handle Muzix already exists exceptions
    @ExceptionHandler(MuzixAlreadyExistsException.class)
    public ResponseEntity handleMuzixAlreadyExistsException(final MuzixAlreadyExistsException e){
        return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
    }
}
