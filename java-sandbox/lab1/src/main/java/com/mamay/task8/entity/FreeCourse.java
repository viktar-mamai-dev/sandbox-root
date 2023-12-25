package com.mamay.task8.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
public class FreeCourse extends BaseCourse {
  private boolean select;

  public FreeCourse(int id, String name, int mark, boolean select) {
    super(id, name, mark);
    this.select = select;
  }
}
