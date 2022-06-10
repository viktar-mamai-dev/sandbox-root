package com.mamay.task2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class BaseEmployee extends Person {
	private int income;

	public BaseEmployee(String name, Address address) {
		super(name, address);
	}

	public BaseEmployee(String name, Address address, int income) {
		super(name, address);
		this.income = income;
	}

}
