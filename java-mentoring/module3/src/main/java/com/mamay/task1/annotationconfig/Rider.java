package com.mamay.task1.annotationconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Rider {

    @Value(value = "Tom")
    private String name;
    @Value(value = "1")
    private Integer ranking;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Rider [name=" + name + ", ranking=" + ranking + "]";
    }

}
