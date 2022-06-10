package com.mamay.task2.entity;

import com.mamay.task2.exception.LogicalException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Worker extends BaseEmployee {

	private WorkerType type;

	public Worker(String name, Address address, int income, WorkerType type) throws LogicalException {
		super(name, address);
		setType(type);
		setIncome(income);
	}

	public void setIncome(int income) throws LogicalException {
		if (income >= type.getMinIncome() && income <= type.getMaxIncome()) {
			super.setIncome(income);
		} else {
			throw new LogicalException("Incorrect income for worker " + super.getName());
		}
	}
}
