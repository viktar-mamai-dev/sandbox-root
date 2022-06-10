package com.mamay.task8.service;

import com.mamay.task8.entity.AbstractCourse;
import com.mamay.task8.entity.CourseType;
import com.mamay.task8.entity.Student;
import com.mamay.task8.factory.CourseFactory;

import java.util.LinkedList;

/**
 * Created by admin on 9/24/2014.
 */
public class Task8Runner {
    public static void main(String[] args) {
        Student student = new Student(322802, 95, new LinkedList<AbstractCourse>());
        CourseService action = new CourseService();
        action.assignCourses(student, 12);
        StudentReport report = new StudentReport();
        report.printReport(student);
        CourseFactory factory = CourseFactory.getInstance();
        AbstractCourse course = factory.getCourseFromFactory(CourseType.FREE);
    }
}
