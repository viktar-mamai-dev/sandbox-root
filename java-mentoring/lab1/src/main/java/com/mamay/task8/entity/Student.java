package com.mamay.task8.entity;

import com.mamay.task8.exception.LogicException;
import com.mamay.task8.exception.TechnicalException;
import java.util.ArrayList;
import java.util.List;

/** Created by admin on 9/22/2014. */
public class Student {
  private int id;
  private double weight;
  private List<AbstractCourse> courses; // = new ArrayList<>();

  public Student(int id, double weight, ArrayList<AbstractCourse> courses) {
    this.id = id;
    this.weight = weight;
    this.courses = courses;
  }

  public Student(int id, double weight, List<AbstractCourse> courses) {
    this.id = id;
    this.weight = weight;
    this.courses = courses;
  }

  public boolean addCourse(AbstractCourse course) {
    return courses.add(course);
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) throws LogicException {
    if (id > 0) {
      this.id = id;
    } else {
      throw new LogicException("incorrect id");
    }
  }

  public void stringToId(String strId) throws TechnicalException {
    try {
      id = Integer.parseInt(strId);
    } catch (NumberFormatException e) {
      throw new TechnicalException(e);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Student student = (Student) o;

    if (id != student.id) return false;
    return Double.compare(student.weight, weight) == 0;
  }

  @Override
  public int hashCode() {
    long temp = Double.doubleToLongBits(weight);
    long result = 31 * id + (int) (temp ^ (temp >>> 32));
    return (int) result;
  }

  @Override
  public String toString() {
    return "Student{" + "id=" + id + ", weight=" + weight + ", courses=" + courses + '}';
  }
}
