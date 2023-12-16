package com.mamay;

import com.mamay.task2.entity.BaseEmployee;
import com.mamay.task2.entity.Person;
import com.mamay.task2.entity.Project;
import com.mamay.task2.exception.LogicalException;
import com.mamay.task2.util.JsonReader;
import com.mamay.task2.util.PrintReport;
import java.io.IOException;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.json.simple.parser.ParseException;

@Log4j2
public class Task2Runner {

  private static final JsonReader reader = new JsonReader();

  public static void main(String[] args) {
    List<BaseEmployee> actors;
    List<BaseEmployee> directors;
    List<BaseEmployee> workers;

    List<Person> persons;
    try {
      actors = reader.initActors();
      directors = reader.initDirectors();
      workers = reader.initWorkers();

      Project project = new Project();
      project.addStuffToProject(actors);
      project.addStuffToProject(directors);
      project.addStuffToProject(workers);

      PrintReport.printAboutAllStuff(project);

      persons = reader.initPersons();
      PrintReport.printAboutPersons(persons);

      PrintReport.printTotalIncome(project);

    } catch (IOException | ParseException | LogicalException e) {
      log.error(e.getMessage());
    }
  }
}
