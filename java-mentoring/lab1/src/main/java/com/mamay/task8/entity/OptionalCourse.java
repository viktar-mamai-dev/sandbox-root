package com.mamay.task8.entity;

import java.time.LocalDate;
import lombok.ToString;

@ToString(callSuper = true)
public class OptionalCourse extends AbstractCourse {
  private LocalDate date;

  public OptionalCourse(int id, String name) {
    super(id, name);
    this.date = LocalDate.now();
  }
}
