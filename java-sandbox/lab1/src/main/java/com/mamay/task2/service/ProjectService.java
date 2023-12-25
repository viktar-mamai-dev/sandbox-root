package com.mamay.task2.service;

import com.mamay.task2.entity.BaseEmployee;
import com.mamay.task2.entity.Project;

public class ProjectService {
  public int calculateTotalIncome(Project project) {
    return project.getProjectStuff().stream()
            .mapToInt(BaseEmployee::getIncome)
            .sum();
  }

  public double calculateAverageIncome(Project project) {
    double size = project.getProjectStuff().size();
    return calculateTotalIncome(project) / size;
  }
}
