/*
 * Copyright (c) 2023
 */
package com.mamay.task1.javaconfig;

import static java.lang.System.out;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    try (context) {
      context.register(JavaBeanConfig.class);
      context.refresh();

      out.println(context.getBean("race1", Race.class));
      out.println(context.getBean("race2", Race.class));
    } catch (BeansException e) {
      out.println(e.getMessage());
    }
  }
}
