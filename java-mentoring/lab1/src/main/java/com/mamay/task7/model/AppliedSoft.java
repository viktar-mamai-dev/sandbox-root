package com.mamay.task7.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class AppliedSoft extends BasicSoft {
  protected int releaseYear;

  public AppliedSoft(String name, double price, double size, int releaseYear) {
    super(name, price, size);
    this.releaseYear = releaseYear;
  }
}
