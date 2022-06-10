package com.mamay.exception;

public class ControllerException extends Exception {

	public ControllerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ControllerException(String arg0) {
		super(arg0);
	}

	public ControllerException(Throwable arg0) {
		super(arg0);
	}

}
