package com.waleryn.fitapp.exception;

public class UserAlreadyLoggedException extends RuntimeException {

    public UserAlreadyLoggedException(String message) {
        super(message);
    }
}
