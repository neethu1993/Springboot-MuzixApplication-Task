package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(MuzixNotFoundException.class)
    public ResponseEntity handleMuzixNotFoundException(final MuzixNotFoundException e) {
        return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MuzixAlreadyExistsException.class)
    public ResponseEntity handleMuzixAlreadyExistsException(final MuzixAlreadyExistsException e){
        return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
    }
}
