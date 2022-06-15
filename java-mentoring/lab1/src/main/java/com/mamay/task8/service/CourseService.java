package com.mamay.task8.service;

import com.mamay.task8.entity.CourseType;
import com.mamay.task8.entity.Student;
import com.mamay.task8.factory.CourseFactory;

/**
 * Created by admin on 9/24/2014.
 */
public class CourseService {

    public void assignCourses(Student student, int number) {
        for (int i = 0; i < number; i++) {

            int mode = Math.round((float) Math.random() * 2) + 1;
            CourseType type = switch (mode) {
                case 1 -> CourseType.BASE;
                case 2 -> CourseType.FREE;
                case 3 -> CourseType.OPTIONAL;
                default -> throw new IllegalStateException("Unexpected value: " + mode);
            };

            student.addCourse(CourseFactory.getInstance().getCourseFromFactory(type));
        }
    }

}
