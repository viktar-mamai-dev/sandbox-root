/*
 * Copyright (c) 2023
 */
package com.mamay.task1.annotationconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class Rider {

  @Value(value = "Tom")
  private String name;

  @Value(value = "1")
  private Integer ranking;
}
