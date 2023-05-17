/*
 * Copyright (c) 2023
 */
package com.mamay.memoryerrors;

public class B {

  private A instance = null;

  public B(int iteration) {
    instance = new A(++iteration);
  }
}
