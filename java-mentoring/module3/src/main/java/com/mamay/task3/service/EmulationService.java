/*
 * Copyright (c) 2023
 */
package com.mamay.task3.service;

import static java.lang.System.out;

import com.mamay.task3.entity.Emulation;
import com.mamay.task3.entity.Horse;
import com.mamay.task3.entity.Race;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class EmulationService {

  private final int LOWER_PACE = 1;
  private final Random random = new Random();
  private List<Emulation> emulations;
  private RaceService raceService;
  private HorseService horseService;

  public void addEmulation(Emulation emulation) {
    this.emulations.add(emulation);
  }

  public void start() throws InterruptedException {
    Emulation currentEmulation = emulations.get(emulations.size() - 1);
    out.println("\nRace started");
    while (!currentEmulation.isFinished()) {
      doProgress(currentEmulation);
      checkFinished(currentEmulation);
    }

    out.println("Race finished\n");
    int indexForWinner = findWinnerIndex(currentEmulation);
    Long winner =
        raceService.retrieve(currentEmulation.getRaceId()).getParticipants().get(indexForWinner);
    if (currentEmulation.getAnimalId() == winner) {
      out.println("Congratulations! You are the winner!\n");
    } else {
      out.println("No luck this time. But now worries. It is just a bet!\n");
    }
  }

  public void doProgress(Emulation emulation) throws InterruptedException {
    TimeUnit.SECONDS.sleep(1);
    Race race = raceService.retrieve(emulation.getRaceId());
    IntStream.range(0, race.getPositions().size())
        .forEach(i -> race.changePositions(i, LOWER_PACE + random.nextDouble() * 10));
    logCurrentState(emulation);
  }

  private void checkFinished(Emulation emulation) {
    Race race = raceService.retrieve(emulation.getRaceId());
    if (race.isFinished()) {
      emulation.setFinished(true);
    }
  }

  private void logCurrentState(Emulation emulation) {
    out.println("\nCurrent state on the race");
    Race race = raceService.retrieve(emulation.getRaceId());
    List<Long> p = race.getParticipants();
    List<Horse> horses = horseService.retrieveRange(p);
    for (Horse horse : horses) {
      out.println(
          "Horse "
              + horse.getName()
              + " with rider "
              + horse.getRider().getName()
              + " at position "
              + race.getPositions().get(horses.indexOf(horse)));
    }
    out.println();
  }

  private int findWinnerIndex(Emulation emulation) {
    Race race = raceService.retrieve(emulation.getRaceId());
    return race.biggestPosition();
  }

  public void setEmulations(List<Emulation> emulations) {
    this.emulations = emulations;
  }

  public RaceService getRaceService() {
    return raceService;
  }

  public void setRaceService(RaceService raceService) {
    this.raceService = raceService;
  }

  public HorseService getHorseService() {
    return horseService;
  }

  public void setHorseService(HorseService horseService) {
    this.horseService = horseService;
  }

  public boolean moreRacesExist() {
    return !raceService.retrieveAll().isEmpty();
  }

  public void showAvailableRaces() {
    printListOfObjects(raceService.retrieveAll());
  }

  public void showAvailableHorsesInRace(Long raceId) {
    Race race = raceService.retrieve(raceId);
    List<Long> p = race.getParticipants();
    printListOfObjects(horseService.retrieveRange(p));
  }

  private void printListOfObjects(List<?> objList) {
    objList.stream().forEach(out::println);
  }

  public boolean existsRace(Long raceId) {
    return raceService.retrieve(raceId) != null;
  }

  public boolean existsHorseInRace(Long raceId, Long horseId) {
    Race race = raceService.retrieve(raceId);
    List<Long> p = race.getParticipants();
    return p.contains(horseId);
  }

  public void setRaceDone(Long raceId) {
    raceService.deleteRace(raceId);
  }
}
