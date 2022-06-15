package com.mamay.task3.dao;

import com.mamay.task3.entity.Horse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HorseDao {

    private Map<Long, Horse> horseMap;

    public Map<Long, Horse> getHorseMap() {
        return horseMap;
    }

    public void setHorseMap(Map<Long, Horse> horseMap) {
        this.horseMap = horseMap;
    }

    public Horse retrieve(Long id) {
        return horseMap.get(id);
    }

    public List<Horse> retrieveRange(List<Long> p) {
        return horseMap.entrySet().stream()
                .filter(entry -> p.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
