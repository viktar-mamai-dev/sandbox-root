package com.mamay.entity;

import org.apache.log4j.Logger;

public class Dog extends Animal {

	private final static Logger LOGGER = Logger.getLogger(Dog.class);

	public Dog(String name) {
		super(name);
	}

	@Override
	public void play() {
		LOGGER.info(getName() + " is playing with ball");

	}

	@Override
	public void voice(String word) {
		LOGGER.info("Dog " + getName() + " is barking " + word);
	}

}
