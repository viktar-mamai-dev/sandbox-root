package com.mamay.task8.service;

import com.mamay.task8.entity.Student;
import lombok.extern.log4j.Log4j2;

/** Created by admin on 10/1/2014. */
@Log4j2
public class StudentReport {
  public void printReport(Student student) {
    log.debug("-----------------Student Report begin-----------------");
    log.debug(student);
    log.debug("-----------------Student Report  end-----------------");
  }
}
