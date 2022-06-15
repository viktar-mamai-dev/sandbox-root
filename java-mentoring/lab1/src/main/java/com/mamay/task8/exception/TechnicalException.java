package com.mamay.task8.exception;

/**
 * Created by admin on 9/22/2014.
 */
public class TechnicalException extends Exception{

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }
}
