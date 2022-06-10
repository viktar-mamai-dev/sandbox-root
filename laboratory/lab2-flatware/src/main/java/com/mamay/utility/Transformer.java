package com.mamay.utility;

import java.util.HashSet;
import java.util.Set;

import com.mamay.entity.Flatware;
import com.mamay.entity.PrimaryFlatware;
import com.mamay.entity.SecondaryFlatware;

public class Transformer {
	public static Set<PrimaryFlatware> extractPrimaryFwares(Set<Flatware> flatwares) {
		Set<PrimaryFlatware> primary = new HashSet<PrimaryFlatware>();
		for (Flatware f : flatwares) {
			if (f instanceof PrimaryFlatware) {
				primary.add((PrimaryFlatware) f);
			}
		}
		return primary;
	}

	public static Set<SecondaryFlatware> extractSecondaryFwares(Set<Flatware> flatwares) {
		Set<SecondaryFlatware> second = new HashSet<SecondaryFlatware>();
		for (Flatware f : flatwares) {
			if (f instanceof SecondaryFlatware) {
				second.add((SecondaryFlatware) f);
			}
		}
		return second;
	}

}
