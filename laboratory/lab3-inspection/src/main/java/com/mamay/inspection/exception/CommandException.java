package com.mamay.inspection.exception;

import javax.servlet.ServletException;

/**
 * <p>
 * occures when something wrong is in the command layer
 *
 * @see DAOException
 * @see LogicException
 */
public class CommandException extends ServletException {
    private static final long serialVersionUID = 1L;

    public CommandException() {
        super();
    }

    public CommandException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public CommandException(String arg0) {
        super(arg0);
    }

    public CommandException(Throwable arg0) {
        super(arg0);
    }

}
