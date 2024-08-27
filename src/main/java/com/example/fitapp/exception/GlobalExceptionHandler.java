package com.example.fitapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
            InvalidProductTypeException.class,
            WrongPasswordException.class,
            PasswordsNotMatchingException.class,
            WrongLogoutTokenException.class,
            UserDoesNotExistException.class,
            UserAlreadyLoggedException.class,
            UserAlreadyExistsException.class,
            DailyNeedsNotAssignedException.class,
            ProductAlreadyExistsException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomExceptions(RuntimeException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex instanceof PasswordsNotMatchingException
                || ex instanceof WrongLogoutTokenException) {
            status = HttpStatus.EXPECTATION_FAILED;
        }

        logger.error("Exception: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), status);

        return new ResponseEntity<>(errorResponse, status);
    }

}
