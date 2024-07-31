package com.example.fitapp.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String message) {
        super(message);
    }
}
