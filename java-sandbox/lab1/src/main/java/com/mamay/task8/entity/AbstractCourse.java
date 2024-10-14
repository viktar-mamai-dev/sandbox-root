package com.mamay.task8.entity;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Created by admin on 9/24/2014. */
@NoArgsConstructor
@ToString
public abstract class AbstractCourse {
  @Getter @Setter private int id;
  @Getter @Setter private String name;
  @Getter @Setter private ArrayList<String> lectors;

  public AbstractCourse(int id) {
    super();
    this.id = id;
  }

  public AbstractCourse(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public boolean add(String s) {
    return lectors.add(s);
  }

  public String get(int index) {
    return lectors.get(index);
  }
}
