package com.mamay.task6.exception;

public class LogicException extends Exception {
  private static final long serialVersionUID = -6032195865404759495L;

  public LogicException(String message, Throwable cause) {
    super(message, cause);
  }

  public LogicException(String message) {
    super(message);
  }

  public LogicException(Throwable cause) {
    super(cause);
  }
}
