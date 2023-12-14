package nl.novi.backend.les12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import nl.novi.backend.les12.exception.DuplicateException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Object> duplicateException (DuplicateException duplicateException) {
        return new ResponseEntity<>(duplicateException.getMessage(), HttpStatus.CONFLICT);
    }

}
