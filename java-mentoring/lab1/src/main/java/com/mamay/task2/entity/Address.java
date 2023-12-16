/*
 * Copyright (c) 2023
 */
package com.mamay.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  private String country;
  private String city;
  private String street;
  private long houseNumber;
}
