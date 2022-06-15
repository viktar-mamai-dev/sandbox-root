package com.mamay.task2.exception;

public class LogicalException extends RuntimeException {
    private static final long serialVersionUID = 9179992351315609075L;

    public LogicalException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public LogicalException(String arg0) {
        super(arg0);
    }

    public LogicalException(Throwable arg0) {
        super(arg0);
    }

}
