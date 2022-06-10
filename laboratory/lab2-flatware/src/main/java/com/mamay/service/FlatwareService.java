package com.mamay.service;

import com.mamay.entity.Flatware;
import com.mamay.entity.PrimaryFlatware;
import com.mamay.entity.SecondaryFlatware;
import com.mamay.type.FlatwareType;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class FlatwareService {
    public int calculateTotalPrice(Set<Flatware> flatwares) {
        int totalPrice = 0;
        for (Flatware fw : flatwares) {
            if (fw instanceof PrimaryFlatware) {
                totalPrice += ((PrimaryFlatware) fw).getPrice();
            }
        }
        return totalPrice;
    }

    public double calcAverageDurability(Set<Flatware> flatwares) {
        return flatwares.stream().filter(f -> f instanceof SecondaryFlatware)
                .mapToDouble(f -> ((SecondaryFlatware) f).getDurability())
                .average().orElse(0D);
    }

    public int countValueFlatwares(Set<Flatware> flatwares) {
        return (int) flatwares.stream().filter(Flatware::isValue).count();
    }

    public Flatware findEarliestProdDate(Set<Flatware> flatwares) {
        return flatwares.stream().filter(f -> f instanceof SecondaryFlatware)
                .min(Comparator.comparing(f -> ((SecondaryFlatware) f).getProductionDate()))
                .orElse(new SecondaryFlatware());
    }

    public Set<Flatware> makeValueorKnifeSet(Set<Flatware> flatwares) {
        return flatwares.stream().filter(f -> f.isValue() || f.getFlatware().equals(FlatwareType.KNIFE))
                .collect(Collectors.toSet());
    }
}
