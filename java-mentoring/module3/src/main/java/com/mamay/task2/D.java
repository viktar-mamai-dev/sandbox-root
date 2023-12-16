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
public class D {

  private static int nextId = 1;
  private Boolean valid;

  public void init() {
    ++nextId;
  }
}
