/*
 * Copyright (c) 2023
 */
package com.mamay.memoryerrors;

public class BigObject {

  private final BigObject obj;
  String str = getClass().getName();

  public BigObject(BigObject obj) {

    this.obj = obj;
    if (obj != null) {
      str = obj.str.concat(obj.str);
    }
  }

  public BigObject getObj() {
    return obj;
  }
}
