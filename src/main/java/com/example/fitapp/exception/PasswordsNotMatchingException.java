package com.example.fitapp.exception;

public class PasswordsNotMatchingException extends RuntimeException {

    public PasswordsNotMatchingException(String message) {
        super(message);
    }
}
