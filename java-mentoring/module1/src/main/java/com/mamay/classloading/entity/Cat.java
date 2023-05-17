package com.mamay.classloading.entity;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void play() {
        log.info(getName() + " is playing with mouse");

    }

    @Override
    public void voice(String word) {
        log.info("Cat " + getName() + " is saying " + word);
    }

}
