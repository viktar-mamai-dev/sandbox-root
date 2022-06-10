package com.mamay.entity;

public abstract class Animal {

	private String name;

	public Animal(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void play();

	public abstract void voice(String word);
}
