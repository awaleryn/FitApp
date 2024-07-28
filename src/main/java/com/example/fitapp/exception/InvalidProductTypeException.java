package com.example.fitapp.exception;

public class InvalidProductTypeException extends RuntimeException  {

    public InvalidProductTypeException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidProductTypeException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }


}
