package com.mamay.task3.exception;

public class LogicException extends Exception {
	private static final long serialVersionUID = -4667739840021051121L;

	public LogicException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LogicException(String arg0) {
		super(arg0);
	}

	public LogicException(Throwable arg0) {
		super(arg0);
	}

}
