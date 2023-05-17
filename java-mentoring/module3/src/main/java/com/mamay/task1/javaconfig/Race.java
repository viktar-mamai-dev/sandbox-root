/*
 * Copyright (c) 2023
 */
package com.mamay.task1.javaconfig;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Race extends IdEntity {

  private String place;
  private LocalDateTime time;
  private List<Horse> horses;
  private int length;
}
