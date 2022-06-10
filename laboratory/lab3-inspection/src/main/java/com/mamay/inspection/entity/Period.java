package com.mamay.inspection.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Period extends Entity {
	private static final long serialVersionUID = -7318413421736537304L;

	private String periodicity;

	public Period(String periodicity) {
		this.periodicity = periodicity;
	}

	public Period(int id, String periodicity) {
		super(id);
		this.periodicity = periodicity;
	}

}
