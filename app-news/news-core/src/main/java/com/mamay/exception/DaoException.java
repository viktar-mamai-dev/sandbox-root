package com.mamay.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 7452053758054555051L;

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
