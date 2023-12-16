package com.mamay.task8.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

/** Created by admin on 9/30/2014. */
@NoArgsConstructor
@ToString(callSuper = true)
public class BaseCourse extends AbstractCourse {
  private int mark;

  public BaseCourse(int id, String name, int mark) {
    super(id, name);
    this.mark = mark;
  }

  public BaseCourse(int id) {
    super(id);
  }
}
