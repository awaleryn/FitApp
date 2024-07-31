package com.example.fitapp.exception;

public class WrongLogoutTokenException extends RuntimeException {

    public WrongLogoutTokenException(String message) {
        super(message);
    }
}
