package com.mamay.task3.service;

import com.mamay.task3.dao.HorseDao;
import com.mamay.task3.entity.Horse;

import java.util.List;

public class HorseService extends AnimalService<Horse> {

    private HorseDao horseDao;

    public Horse retrieve(Long horseId) {
        return horseDao.retrieve(horseId);
    }

    public HorseDao getHorseDao() {
        return horseDao;
    }

    public void setHorseDao(HorseDao horseDao) {
        this.horseDao = horseDao;
    }

    public List<Horse> retrieveRange(List<Long> p) {
        return horseDao.retrieveRange(p);
    }

}
