/*
 * Copyright (c) 2023
 */
package com.mamay.task3.service;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalService<T> {

  private List<T> animals;
}
