package com.mamay.task7.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class TextEditor extends AppliedSoft {

	protected boolean openCode;

	public TextEditor(String name, double price, double size, int releaseYear, boolean openCode) {
		super(name, price, size, releaseYear);
		this.openCode = openCode;
	}
}
