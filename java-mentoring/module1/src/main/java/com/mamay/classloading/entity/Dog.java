package com.mamay.classloading.entity;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void play() {
        log.info(getName() + " is playing with ball");
    }

    @Override
    public void voice(String word) {
        log.info("Dog " + getName() + " is barking " + word);
    }

}
