/*
 * Copyright (c) 2023
 */
package com.mamay.classloading.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Animal {

  private String name;

  public Animal(String name) {
    this.name = name;
  }

  public abstract void play();

  public abstract void voice(String word);
}
