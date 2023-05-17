package com.mamay.task2.util;

import com.mamay.task2.entity.Actor;
import com.mamay.task2.entity.BaseEmployee;
import com.mamay.task2.entity.Director;
import com.mamay.task2.entity.Person;
import com.mamay.task2.entity.Project;
import com.mamay.task2.entity.Worker;
import com.mamay.task2.service.ProjectService;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PrintReport {
  private static final ProjectService service = new ProjectService();

  public static void printAboutAllStuff(Project project) {
    log.debug("----------------Project report------------------");
    log.debug(project.toString());
    log.debug("------------------------------------------------");
  }

  public static void printAboutActors(Project project) {
    log.info("----------------Actors report-------------------");
    for (BaseEmployee emp : project.getProjectStuff()) {
      if (emp instanceof Actor) {
        log.debug(emp.toString());
      }
    }
    log.debug("------------------------------------------------");
  }

  public static void printAboutDirectors(Project project) {
    log.debug("----------------Directors report------------------");
    for (BaseEmployee emp : project.getProjectStuff()) {
      if (emp instanceof Director) {
        log.debug(emp.toString());
      }
    }
    log.debug("------------------------------------------------");
  }

  public static void printAboutWorkers(Project project) {
    log.debug("----------------Workers report--------------------");
    for (BaseEmployee emp : project.getProjectStuff()) {
      if (emp instanceof Worker) {
        log.debug(emp.toString());
      }
    }
    log.debug("------------------------------------------------");
  }

  public static void printAboutPersons(List<Person> persons) {
    log.debug("----------------Person report-------------------");
    for (Person person : persons) {
      log.debug(person.toString());
    }
    log.debug("------------------------------------------------");
  }

  public static void printTotalIncome(Project project) {
    int totalIncome = service.calculateTotalIncome(project);
    log.debug("Total income of project is: " + totalIncome);
  }
}
