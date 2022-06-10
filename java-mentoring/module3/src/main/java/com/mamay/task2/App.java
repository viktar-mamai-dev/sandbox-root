package com.mamay.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("task2-applicationContext.xml");

        try {
            A a1 = context.getBean("a1", A.class);
            LOGGER.info(String.format("Initializing object of class A: %s", a1));

            B b1 = context.getBean("b1", B.class);
            LOGGER.info(String.format("Initializing object of class B: %s", b1));

            C c1 = context.getBean("c1", C.class);

            D d1 = c1.createValidD();
            LOGGER.info(String.format("Object of type D: %s", d1));
            D d2 = c1.createValidD();
            LOGGER.info(String.format("One more object of type D: %s", d2));

            D d3 = c1.createInvalidD();
            LOGGER.info(String.format("One more object of type D: %s", d3));

            E e1 = context.getBean("e1", E.class);
            LOGGER.info(String.format("Result: %s", e1.addition(3, 5)));
            LOGGER.info(String.format("Result: %s", e1.multiplication(8, 5)));

            context.getBean("f1", F.class);

        } catch (BeansException e) {
            LOGGER.error("Something wrong happened during bean initialization", e);
        } finally {
            context.close();
        }
    }
}
