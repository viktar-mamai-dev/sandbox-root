package com.mamay.inspection.exception;

import java.sql.SQLException;

/**
 * occures when something wrong is in the DAO layer or there are some problems with Connection pool
 */
public class DAOException extends SQLException {
  private static final long serialVersionUID = 3448481773065723952L;

  public DAOException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DAOException(String arg0) {
    super(arg0);
  }

  public DAOException(Throwable arg0) {
    super(arg0);
  }
}
