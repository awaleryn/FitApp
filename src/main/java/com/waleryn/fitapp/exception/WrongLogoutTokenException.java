package com.waleryn.fitapp.exception;

public class WrongLogoutTokenException extends RuntimeException {

    public WrongLogoutTokenException(String message) {
        super(message);
    }
}
