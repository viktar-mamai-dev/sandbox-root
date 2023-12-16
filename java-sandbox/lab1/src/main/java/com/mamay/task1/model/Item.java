/*
 * Copyright (c) 2023
 */
package com.mamay.task1.model;

import lombok.Getter;

public enum Item {
  FISH("fish", 40),
  TURTLE("turtle", 50),
  SHELL("shell"),
  SAND("sand"),
  ALGA("alga"),
  CASTLE("submarine castle"),
  TOOLS("aquarium tools"),
  WATER;

  @Getter private String name;
  @Getter private int cost;

  Item() {}

  Item(String name) {
    this.name = name;
  }

  Item(String name, int cost) {
    this.name = name;
    this.cost = cost;
  }
}
