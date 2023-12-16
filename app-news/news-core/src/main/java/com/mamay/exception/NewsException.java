package com.mamay.exception;

public class NewsException extends RuntimeException {

    private static final long serialVersionUID = 7452053758054555051L;

    public NewsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsException(String message) {
        super(message);
    }

    public NewsException(Throwable cause) {
        super(cause);
    }

}
