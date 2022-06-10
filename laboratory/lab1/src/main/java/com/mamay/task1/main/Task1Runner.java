package com.mamay.task1.main;

import com.mamay.task1.exception.AquaException;
import com.mamay.task1.model.Aquarium;
import com.mamay.task1.model.Item;
import com.mamay.task1.util.MessageLogger;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Task1Runner {

    public static void main(String[] args) {
        testAquarium1();
    }

    public static void testAquarium1() {
        Aquarium aqua1 = new Aquarium(478, "Table Aquarium", Item.TURTLE, 1);
        Aquarium aqua2 = new Aquarium(578, "Rectangular Aquarium ", Item.FISH, 3);

        try {
            aqua1.newComponent(Item.SAND);
            aqua1.newComponent(Item.SHELL);
            aqua1.newComponent(Item.TURTLE);
            aqua1.print();
            log.debug("printing Info about aqua1");

            log.debug("Customer id: " + aqua1.getCustomerId()
                    + " Count of orders: " + aqua1.getCountOfOrders()
                    + " Name: " + aqua1.getName());

            aqua2.newComponent(Item.FISH);
            aqua2.newComponent(Item.CASTLE);
            aqua2.newComponent(Item.ALGA);
            aqua2.newComponent(Item.TOOLS);
            aqua2.print();

            log.debug("Customer id: " + aqua2.getCustomerId() + " Count of orders: "
                    + aqua2.getCountOfOrders() + " Name: " + aqua2.getName());

            aqua2.newComponent(Item.TOOLS);

            log.debug("Orders in aqua2: " + aqua2.getCountOfOrders());

            aqua2.newComponent(Item.WATER);

        } catch (AquaException e) {
            log.error(e.getMessage());
        }

        MessageLogger info = new MessageLogger();
        info.printCheck(aqua1);
        info.printCheck(aqua2);
    }
}
