package com.mamay.task2.util;

import com.mamay.Lab1Exception;
import com.mamay.task2.entity.Actor;
import com.mamay.task2.entity.Address;
import com.mamay.task2.entity.BaseEmployee;
import com.mamay.task2.entity.Director;
import com.mamay.task2.entity.Person;
import com.mamay.task2.entity.Worker;
import com.mamay.task2.entity.WorkerType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

  private static final String CINEMA_EMP_FILE = "task2/cinemaEmployee.txt";
  private static final String PERSON_FILE = "task2/person.txt";

  private static final JSONParser parser = new JSONParser();

  private final File CINEMA_INPUT;
  private final File PERSON_INPUT;

  public JsonReader() {
    CINEMA_INPUT = returnFile(CINEMA_EMP_FILE);
    PERSON_INPUT = returnFile(PERSON_FILE);
  }

  private File returnFile(String fileName) {
    URL resource = getClass().getClassLoader().getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("File not found " + fileName);
    } else {
      return new File(resource.getFile());
    }
  }

  public List<BaseEmployee> initActors() throws IOException, ParseException, Lab1Exception {
    List<BaseEmployee> actors = new ArrayList<BaseEmployee>();
    JSONObject object = (JSONObject) parser.parse(new FileReader(CINEMA_INPUT));
    JSONArray array = (JSONArray) object.get("actors");
    for (Object anArray : array) {
      JSONObject element = (JSONObject) anArray;
      String name = (String) element.get("name");
      JSONObject addr = (JSONObject) element.get("address");
      Address address = convertToAddress(addr);
      Long income = (Long) element.get("income");
      String description = (String) element.get("description");
      Actor actor;
      if (description != null) {
        actor = new Actor(name, address, income.intValue(), description);
      } else {
        actor = new Actor(name, address, income.intValue());
      }
      actors.add(actor);
    }
    return actors;
  }

  public List<BaseEmployee> initDirectors() throws IOException, ParseException, Lab1Exception {
    List<BaseEmployee> directors = new ArrayList<BaseEmployee>();
    JSONObject object = (JSONObject) parser.parse(new FileReader(CINEMA_INPUT));
    JSONArray array = (JSONArray) object.get("directors");
    for (Object anArray : array) {
      JSONObject element = (JSONObject) anArray;
      String name = (String) element.get("name");
      JSONObject addr = (JSONObject) element.get("address");
      Address address = convertToAddress(addr);
      Long income = (Long) element.get("income");
      JSONArray filmArray = (JSONArray) element.get("films");
      Director director = new Director(name, address, income.intValue());
      List<String> films = new ArrayList<String>();
      for (Object aFilmArray : filmArray) {
        films.add(aFilmArray.toString());
      }
      director.addFilms(films);
      directors.add(director);
    }
    return directors;
  }

  public List<BaseEmployee> initWorkers() throws IOException, ParseException, Lab1Exception {
    List<BaseEmployee> workers = new ArrayList<BaseEmployee>();
    JSONObject object = (JSONObject) parser.parse(new FileReader(CINEMA_INPUT));
    JSONArray array = (JSONArray) object.get("workers");
    for (Object anArray : array) {
      JSONObject element = (JSONObject) anArray;
      String name = (String) element.get("name");
      JSONObject addr = (JSONObject) element.get("address");
      Address address = convertToAddress(addr);
      Long income = (Long) element.get("income");
      String elementType = ((String) element.get("type")).toUpperCase();
      WorkerType type = WorkerType.valueOf(elementType);
      Worker worker = new Worker(name, address, income.intValue(), type);
      workers.add(worker);
    }
    return workers;
  }

  public List<Person> initPersons() throws IOException, ParseException, Lab1Exception {
    List<Person> persons = new ArrayList<Person>();
    JSONObject object = (JSONObject) parser.parse(new FileReader(PERSON_INPUT));
    JSONArray array = (JSONArray) object.get("persons");
    for (Object anArray : array) {
      JSONObject element = (JSONObject) anArray;
      String name = (String) element.get("name");
      JSONObject addr = (JSONObject) element.get("address");
      Address address = convertToAddress(addr);
      Person person = new Person(name, address);
      persons.add(person);
    }
    return persons;
  }

  private Address convertToAddress(JSONObject jsonObject) {
    String country = (String) jsonObject.get("country");
    String city = (String) jsonObject.get("city");
    String street = (String) jsonObject.get("street");
    Long houseNumber = (Long) jsonObject.get("houseNumber");
    return new Address(country, city, street, houseNumber.intValue());
  }
}
