package com.mamay.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 9028400421125083122L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
