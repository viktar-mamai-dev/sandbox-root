package com.mamay.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PrimaryFlatware extends Flatware {

  private String id; // attribute - required
  private int price; // element - required
}
