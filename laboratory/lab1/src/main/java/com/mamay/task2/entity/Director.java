package com.mamay.task2.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.NoArgsConstructor;
import lombok.ToString;

import com.mamay.task2.exception.LogicalException;

@ToString
@NoArgsConstructor
public class Director extends BaseEmployee {

	private final int MINIMUM_INCOME = 5000;
	private final int MAXIMUM_INCOME = 20000;

	private Set<String> films;

	public Director(String name, Address address, int income) throws LogicalException {
		super(name, address);
		setIncome(income);
		this.films = new HashSet<String>();
	}

	public void setIncome(int income) throws LogicalException {
		if (income >= MINIMUM_INCOME && income <= MAXIMUM_INCOME) {
			super.setIncome(income);
		} else {
			throw new LogicalException("Incorrect income for director " + super.getName());
		}
	}

	public Set<String> getFilms() {
		return Collections.unmodifiableSet(this.films);
	}

	public void addFilm(String film) {
		this.films.add(film);
	}

	public void addFilms(List<String> films) {
		this.films.addAll(films);
	}

}
