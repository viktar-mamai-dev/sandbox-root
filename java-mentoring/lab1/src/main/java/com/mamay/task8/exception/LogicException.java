package com.mamay.task8.exception;

/**
 * Created by admin on 9/22/2014.
 */
public class LogicException extends Exception {

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }
}
