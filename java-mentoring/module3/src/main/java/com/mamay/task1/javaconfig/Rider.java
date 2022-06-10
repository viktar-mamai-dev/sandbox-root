package com.mamay.task1.javaconfig;

public class Rider {

    private String name;
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
