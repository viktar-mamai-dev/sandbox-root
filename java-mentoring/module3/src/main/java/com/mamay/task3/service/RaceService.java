/*
 * Copyright (c) 2023
 */
package com.mamay.task3.service;

import com.mamay.task3.dao.RaceDao;
import com.mamay.task3.entity.Race;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaceService {

  private RaceDao raceDao;

  public Race retrieve(Long raceId) {
    return raceDao.retrieve(raceId);
  }

  public List<Race> retrieveAll() {
    return raceDao.retrieveAll();
  }

  public void deleteRace(Long raceId) {
    raceDao.delete(raceId);
  }
}
