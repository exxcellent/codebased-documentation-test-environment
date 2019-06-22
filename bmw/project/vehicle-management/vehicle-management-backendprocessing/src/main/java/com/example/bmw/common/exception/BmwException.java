package com.example.bmw.common.exception;

public abstract class BmwException extends RuntimeException {



    protected BmwException(final String message, final Throwable cause) {
        super(message, cause);
    }


    public BmwException(final String message) {
        super(message);
    }
}
