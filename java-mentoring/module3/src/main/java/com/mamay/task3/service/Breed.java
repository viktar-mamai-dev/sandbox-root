package com.mamay.task3.service;

public class Breed {

    private String name;
    private String originCountry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        return "Breed [name=" + name + ", originCountry=" + originCountry + "]";
    }
}
