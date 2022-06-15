package com.mamay.builder;

import com.mamay.entity.Flatware;
import com.mamay.exception.LogicalException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlatwareBuilder {
	protected Set<Flatware> flatwares;

	public AbstractFlatwareBuilder() {
		flatwares = new HashSet<Flatware>();
	}

	public AbstractFlatwareBuilder(Set<Flatware> flatwares) {
		this.flatwares = flatwares;
	}

	public Set<Flatware> getFlatwares() {
		return flatwares;
	}

	abstract public void buildFlatwares(String fileName) throws LogicalException;

}
