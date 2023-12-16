/*
 * Copyright (c) 2023
 */
package com.mamay.task2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class B {

  private A a;

  public B(A a) {
    this.a = a;
  }
}
