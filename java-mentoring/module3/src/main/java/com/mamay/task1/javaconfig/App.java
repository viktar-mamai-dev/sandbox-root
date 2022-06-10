package com.mamay.task1.javaconfig;

import org.springframework.beans.BeansException;

import static java.lang.System.out;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(JavaBeanConfig.class);
        context.refresh();

        try {

            out.println("Java configuration demo");
            Race race1 = context.getBean("race1", Race.class);
            out.println(race1);

            Race race2 = context.getBean("race2", Race.class);
            out.println(race2);
        } catch (BeansException e) {
            out.println(e.getMessage());
        } finally {
            context.close();
        }
    }
}
