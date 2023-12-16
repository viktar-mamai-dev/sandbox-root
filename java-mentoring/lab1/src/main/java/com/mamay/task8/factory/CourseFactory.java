package com.mamay.task8.factory;

import com.mamay.task8.entity.AbstractCourse;
import com.mamay.task8.entity.BaseCourse;
import com.mamay.task8.entity.CourseType;
import com.mamay.task8.entity.FreeCourse;
import com.mamay.task8.entity.OptionalCourse;
import java.util.Random;

/** Created by admin on 10/1/2014. */
public class CourseFactory {
  private static final CourseFactory instance = new CourseFactory();

  private CourseFactory() {}

  public static CourseFactory getInstance() {
    return instance;
  }

  public AbstractCourse getCourseFromFactory(CourseType type) {
    int id = Math.abs(new Random().nextInt());
    int mark = Math.abs(new Random().nextInt(10));
    int version = Math.abs(new Random().nextInt(8));

    return switch (type) {
      case BASE -> new BaseCourse(id, "Java" + version, mark);
      case FREE -> new FreeCourse(id, "Scala" + version, mark, true);
      case OPTIONAL -> new OptionalCourse(id, "Kotlin" + version);
    };
  }
}
