package com.mamay.inspection.exception;

/**
 * <p>
 * occures when something wrong is in the logic
 * 
 * @see DAOException
 * @see CommandException
 */

public class LogicException extends Exception {
	private static final long serialVersionUID = 1L;

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
