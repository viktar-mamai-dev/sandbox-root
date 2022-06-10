package com.mamay.task1.annotationconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Horse extends IdEntity {

    @Value(value = "Thunder")
    private String name;
    @Autowired
    private Rider rider;
    @Autowired
    private Breed breed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Horse [ID=" + getId() + ", name=" + name + ", rider=" + (rider != null ? rider.getName() : "/") + ", breed="
                + (breed != null ? breed.getName() : "/") + "]";
    }
}
