/*
 * Copyright (c) 2023
 */
package com.mamay.task2.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.ToString;

@ToString
public class Project {
  private final ArrayList<BaseEmployee> projectStuff;

  public Project() {
    this.projectStuff = new ArrayList<BaseEmployee>();
  }

  public List<BaseEmployee> getProjectStuff() {
    return Collections.unmodifiableList(this.projectStuff);
  }

  public void addPersonToProject(BaseEmployee emp) {
    this.projectStuff.add(emp);
  }

  public void addStuffToProject(List<BaseEmployee> emps) {
    this.projectStuff.addAll(emps);
  }
}
