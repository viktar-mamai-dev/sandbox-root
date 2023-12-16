package com.mamay.task7.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Browser extends AppliedSoft {
  private String engine;

  public Browser(String name, double price, double size, int releaseYear, String currentCore) {
    super(name, price, size, releaseYear);
    this.engine = currentCore;
  }
}
