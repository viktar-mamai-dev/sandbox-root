package com.mamay.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class SecondaryFlatware extends Flatware {

    private Date productionDate; // required attribute with pattern
    private int durability; // optional attribute
    private Set<String> dishes = new HashSet<String>();

    public void addDish(String dish) {
        this.dishes.add(dish);
    }
}
