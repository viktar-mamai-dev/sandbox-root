package com.mamay.task1.annotationconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Breed {

    @Value(value = "Quarter")
    private String name;
    @Value(value = "USA")
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
