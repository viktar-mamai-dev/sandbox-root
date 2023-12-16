/*
 * Copyright (c) 2023
 */
package com.mamay.task3.dao;

import com.mamay.task3.entity.Race;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaceDao {

  private Map<Long, Race> raceMap;

  public Race retrieve(Long id) {
    return raceMap.get(id);
  }

  public List<Race> retrieveAll() {
    return new ArrayList<>(raceMap.values());
  }

  public void delete(Long id) {
    raceMap.remove(id);
  }
}
