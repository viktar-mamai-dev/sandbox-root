/*
 * Copyright (c) 2023
 */
package com.mamay.task1.annotationconfig;

import static java.lang.System.out;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    try (context) {
      context.register(AnnotationConfig.class);
      context.refresh();
      Race race1 = context.getBean("race1", Race.class);
      out.println(race1);
    } catch (BeansException e) {
      out.println(e.getMessage());
    }
  }
}
