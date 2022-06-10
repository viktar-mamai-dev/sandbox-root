package com.mamay.task3.service;

import java.util.List;

import com.mamay.task3.dao.RaceDao;

public class RaceService {

    private RaceDao raceDao;

    public RaceDao getRaceDao() {
        return raceDao;
    }

    public void setRaceDao(RaceDao raceDao) {
        this.raceDao = raceDao;
    }

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
