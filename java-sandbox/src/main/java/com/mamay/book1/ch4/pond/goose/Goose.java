/*
 * Copyright (c) 2023
 */
package com.mamay.book1.ch4.pond.goose;

import com.mamay.book1.ch4.pond.shore.Bird;

public class Goose extends Bird {
  public void helpGooseSwim() {
    Goose other = new Goose();
    other.floatWater();
    System.out.println(other.text);
  }
}
