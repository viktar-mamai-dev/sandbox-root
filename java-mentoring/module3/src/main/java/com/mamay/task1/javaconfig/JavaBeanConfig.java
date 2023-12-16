/*
 * Copyright (c) 2023
 */
package com.mamay.task1.javaconfig;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBeanConfig {

  @Bean
  public Rider rider1() {
    Rider rider = new Rider();
    rider.setName("John");
    rider.setRanking(1);
    return rider;
  }

  @Bean
  public Rider rider2() {
    Rider rider = new Rider();
    rider.setName("Tom");
    rider.setRanking(2);
    return rider;
  }

  @Bean
  public Rider rider3() {
    Rider rider = new Rider();
    rider.setName("Andrew");
    rider.setRanking(3);
    return rider;
  }

  @Bean
  public Breed breed1() {
    Breed breed = new Breed();
    breed.setName("Quarter");
    breed.setOriginCountry("USA");
    return breed;
  }

  @Bean
  public Breed breed2() {
    Breed breed = new Breed();
    breed.setName("Percheron");
    breed.setOriginCountry("France");
    return breed;
  }

  @Bean
  public Breed breed3() {
    Breed breed = new Breed();
    breed.setName("Clydesdale");
    breed.setOriginCountry("Scotland");
    return breed;
  }

  @Bean
  public Horse horse1() {
    Horse horse = new Horse();
    horse.setId(1l);
    horse.setName("Jaguar");
    horse.setBreed(breed1());
    horse.setRider(rider1());
    return horse;
  }

  @Bean
  public Horse horse2() {
    Horse horse = new Horse();
    horse.setId(2l);
    horse.setName("Cleveland");
    horse.setBreed(breed2());
    horse.setRider(rider2());
    return horse;
  }

  @Bean
  public Horse horse3() {
    Horse horse = new Horse();
    horse.setId(3l);
    horse.setName("Russel");
    horse.setBreed(breed3());
    horse.setRider(rider3());
    return horse;
  }

  @Bean
  public Race race1() {
    Race race = new Race();
    race.setId(1l);
    race.setPlace("Central Ippodrom");
    race.setTime(LocalDateTime.of(2019, Month.JULY, 21, 18, 0));
    race.setLength(100);
    List<Horse> horses = new ArrayList<Horse>();
    horses.add(horse1());
    horses.add(horse2());
    race.setHorses(horses);
    return race;
  }

  @Bean
  public Race race2() {
    Race race = new Race();
    race.setId(2l);
    race.setPlace("Margaret Court Stadium");
    race.setTime(LocalDateTime.of(2019, Month.DECEMBER, 5, 9, 30));
    race.setLength(70);
    List<Horse> horses = new ArrayList<Horse>();
    horses.add(horse3());
    horses.add(horse2());
    race.setHorses(horses);
    return race;
  }
}
