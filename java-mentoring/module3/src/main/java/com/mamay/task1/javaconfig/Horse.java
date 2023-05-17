/*
 * Copyright (c) 2023
 */
package com.mamay.task1.javaconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Horse extends IdEntity {

  private String name;
  private Rider rider;
  private Breed breed;
}
