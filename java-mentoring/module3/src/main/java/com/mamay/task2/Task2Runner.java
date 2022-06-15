package com.mamay.task2;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log4j2
public class Task2Runner {

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("task2-applicationContext.xml")) {
            A a1 = context.getBean("a1", A.class);
            log.info(String.format("Initializing object of class A: %s", a1));

            B b1 = context.getBean("b1", B.class);
            log.info(String.format("Initializing object of class B: %s", b1));

            C c1 = context.getBean("c1", C.class);

            D d1 = c1.createValidD();
            log.info(String.format("Object of type D: %s", d1));
            D d2 = c1.createValidD();
            log.info(String.format("One more object of type D: %s", d2));

            D d3 = c1.createInvalidD();
            log.info(String.format("One more object of type D: %s", d3));

            E e1 = context.getBean("e1", E.class);
            log.info(String.format("Result: %s", e1.addition(3, 5)));
            log.info(String.format("Result: %s", e1.multiplication(8, 5)));

            context.getBean("f1", F.class);

        } catch (BeansException e) {
            log.error("Something wrong happened during bean initialization", e);
        }
    }
}
