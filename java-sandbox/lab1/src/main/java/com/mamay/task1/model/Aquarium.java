/*
 * Copyright (c) 2023
 */
package com.mamay.task1.model;

import com.mamay.Lab1Exception;
import com.mamay.task1.util.AquaContainer;
import com.mamay.task1.util.Generator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Set;

@Log4j2
public class Aquarium {

    @Getter
    private final int id;
    // constants
    private final int MIN_NAME_LENGTH = 4;
    private final int MAX_NAME_LENGTH = 20;
    private final int MIN_ORDERS = 1;
    private final int MAX_ORDERS = 3;
    private final int ID_DIGITS = 5;
    private final int DEFAULT_ORDERS = 1;
    @Getter
    @Setter
    private int customerId;
    @Getter
    private String name;
    @Getter
    @Setter
    private Item type;
    private Set<Component> components;
    @Getter
    private int countOfOrders;

    public Aquarium(int customerId, String name, Item type, int countOfOrders) {
        super();
        this.id = Generator.generateRandomNumber(ID_DIGITS);
        this.setCustomerId(customerId);
        this.setName(name);
        this.setType(type);
        this.setCountOfOrders(countOfOrders);
        this.components = new HashSet<>();
    }

    public void setName(String name) {
        if (name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH) {
            this.name = name;
        } else {
            this.name = "Client#" + this.id;
        }
    }

    public void setCountOfOrders(int countOfOrders) {
        if (countOfOrders >= MIN_ORDERS && countOfOrders <= MAX_ORDERS) {
            this.countOfOrders = countOfOrders;
        } else {
            this.countOfOrders = DEFAULT_ORDERS;
        }
    }

    public Set<Component> getComponents() {
        return components;
    }

    public void addComponent(Item item) throws Lab1Exception {
        AquaContainer aquaContainer = new AquaContainer();
        Component possibleComponent = aquaContainer.getPossibleComponent(item)
                .orElseThrow(() -> new Lab1Exception("Error! Such component does not exist!"));
        for (Component c : components) {
            if (c.getName().equals(item)) {
                log.error("Component " + item.getName() + " already exists!");
                return;
            }
        }
        components.add(possibleComponent);
        if (aquaContainer.isFullAquarium(this)) {
            log.debug("Aquarium is full!");
        }
    }

    public int getTotalCost() {
        return components.stream().mapToInt(Component::getCost).sum()
                + type.getCost();
    }

    @Override
    public String toString() {
        return "Aquarium{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", name='" + name + '\'' +
                ", components=" + components.size() +
                '}';
    }
}
