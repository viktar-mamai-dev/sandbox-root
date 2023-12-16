/*
 * Copyright (c) 2023
 */
package com.mamay.book1.ch4;

public class Koala {
  static int counter = 10;

  public static void main(String[] args) {
    Koala k = null;
    System.out.println(k.counter); // outputs 10 as counter is static
  }
}
