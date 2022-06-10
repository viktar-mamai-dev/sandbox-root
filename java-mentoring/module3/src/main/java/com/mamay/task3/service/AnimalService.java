package com.mamay.task3.service;

import java.util.List;

public class AnimalService<T> {

    private List<T> animals;
    
    public List<T> getAnimals() {
        return animals;
    }

    public void setAnimals(List<T> animals) {
        this.animals = animals;
    }
}
