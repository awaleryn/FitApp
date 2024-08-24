package com.example.fitapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidProductTypeException.class)
    public ResponseEntity<String> handleInvalidProductTypeException(InvalidProductTypeException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(PasswordsNotMatchingException.class)
    public ResponseEntity<String> handlePasswordsNotMatchingException(PasswordsNotMatchingException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.EXPECTATION_FAILED
        );
    }

    @ExceptionHandler(WrongLogoutTokenException.class)
    public ResponseEntity<String> handleWrongLogoutTokenException(WrongLogoutTokenException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> handleUserDoesNotExistException(UserDoesNotExistException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserAlreadyLoggedException.class)
    public ResponseEntity<String> handleUserAlreadyLoggedException(UserAlreadyLoggedException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
