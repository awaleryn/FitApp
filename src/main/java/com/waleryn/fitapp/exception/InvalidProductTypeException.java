package com.waleryn.fitapp.exception;

public class InvalidProductTypeException extends RuntimeException  {

    public InvalidProductTypeException(String errorMessage) {
        super(errorMessage);
    }

}
