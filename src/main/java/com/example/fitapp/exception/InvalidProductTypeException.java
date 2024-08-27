package com.example.fitapp.exception;

public class InvalidProductTypeException extends RuntimeException  {

    public InvalidProductTypeException(String errorMessage) {
        super(errorMessage);
    }

}
