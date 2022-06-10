package com.mamay.entity;

import org.apache.log4j.Logger;

public class Cat extends Animal {

	private final static Logger LOGGER = Logger.getLogger(Cat.class);

	public Cat(String name) {
		super(name);
	}

	@Override
	public void play() {
		LOGGER.info(getName() + " is playing with mouse");

	}

	@Override
	public void voice(String word) {
		LOGGER.info("Cat " + getName() + " is saying " + word);
	}

}
