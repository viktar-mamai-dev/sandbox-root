package com.mamay.task2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.mamay.task2.exception.LogicalException;

@ToString
@Getter
@Setter
public class Actor extends BaseEmployee {

	private final int MINIMUM_INCOME = 3000;
	private final int MAXIMUM_INCOME = 25000;

	private String description;

	public Actor(String name, Address address, int income) throws LogicalException {
		super(name, address);
		setIncome(income);
	}

	public Actor(String name, Address address, int income, String description) throws LogicalException {
		super(name, address);
		setIncome(income);
		setDescription(description);
	}

	public void setIncome(int income) throws LogicalException {
		if (income >= MINIMUM_INCOME && income <= MAXIMUM_INCOME) {
			super.setIncome(income);
		} else {
			throw new LogicalException("Incorrect income for actor " + super.getName());
		}
	}
}
