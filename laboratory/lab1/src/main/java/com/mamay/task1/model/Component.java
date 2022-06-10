package com.mamay.task1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Component {
	@Getter
	@Setter
	private int cost;
	@Getter
	private Item name;

}
