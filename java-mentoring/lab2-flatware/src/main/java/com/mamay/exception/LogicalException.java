package com.mamay.exception;

public class LogicalException extends Exception {
	private static final long serialVersionUID = 9159829979764427556L;

	public LogicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicalException(String message) {
		super(message);
	}

	public LogicalException(Throwable cause) {
		super(cause);
	}

}
