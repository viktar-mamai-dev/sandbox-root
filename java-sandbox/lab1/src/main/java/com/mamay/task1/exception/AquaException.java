/*
 * Copyright (c) 2023
 */
package com.mamay.task1.exception;

public class AquaException extends Exception {
  private static final long serialVersionUID = 5051620890218829517L;

  public AquaException(String message, Throwable cause) {
    super(message, cause);
  }

  public AquaException(String message) {
    super(message);
  }

  public AquaException(Throwable cause) {
    super(cause);
  }
}
