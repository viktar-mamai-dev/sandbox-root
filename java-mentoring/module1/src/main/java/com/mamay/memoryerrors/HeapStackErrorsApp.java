/*
 * Copyright (c) 2023
 */
package com.mamay.memoryerrors;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javassist.CannotCompileException;
import javassist.ClassPool;

public class HeapStackErrorsApp {

  private static int iteration = 1;

  public static void main(String[] args) throws Exception {
    try (Scanner scanner = new Scanner(System.in)) {
      boolean proceed = true;
      while (proceed) {
        showMenu();
        int i = scanner.nextInt();
        switch (i) {
          case 1:
            throwOutOfMemoryError1();
            break;
          case 2:
            throwOutOfMemoryError2();
            break;
          case 3:
            throwOutOfMemoryError3();
            break;
          case 4:
            throwStackOverflowError1();
            break;
          case 5:
            throwStackOverflowError2();
            break;
          default:
            proceed = false;
            break;
        }
      }
    } catch (InterruptedException e) {
      out.println("thread exception");
    } finally {
      out.println("Task is completed");
    }
  }

  private static void throwOutOfMemoryError1() throws Exception {
    final int INIT_SIZE = 2;
    final double COEFF = 1.5;

    int arraySize = INIT_SIZE;
    try {
      while (true) {
        long arr[] = new long[arraySize];
        arraySize *= COEFF;
        TimeUnit.MILLISECONDS.sleep(300);
      }
    } catch (OutOfMemoryError error) {
      out.println("1.OutOfMemory error caught" + error.getMessage());
      out.println("Final array size=" + arraySize);
      error.printStackTrace();
    }
  }

  private static void throwOutOfMemoryError2() {
    BigObject mainObject = new BigObject(null);
    iteration = 0;
    try {
      while (true) {
        mainObject = new BigObject(mainObject);
        iteration++;
      }
    } catch (OutOfMemoryError error) {
      out.println("2.OutOfMemory error caught: " + error.getMessage());
      out.println("Iterations=" + iteration);
      error.printStackTrace();
    }
  }

  private static void throwOutOfMemoryError3() {
    ClassPool cp = ClassPool.getDefault();
    int i = 0;
    try {
      for (i = 0; ; i++) {
        cp.makeClass("com.mamay.GeneratedClass" + i).toClass();
      }
    } catch (OutOfMemoryError error) {
      out.println("3.OutOfMemory error caught");
      error.printStackTrace();
    } catch (CannotCompileException exc) {
      out.println("3.OutOfMemory error caught");
      exc.printStackTrace();
    } finally {
      out.println("Classes loaded=" + i);
    }
  }

  private static void throwStackOverflowError1() {
    try {
      iteration = 0;
      recursiveMethod();
    } catch (StackOverflowError error) {
      out.println("4. StackOverflowError error caught");
      out.println("Iterations=" + iteration);
    }
  }

  private static void throwStackOverflowError2() {
    try {
      new A();
    } catch (StackOverflowError error) {
      out.println("5. StackOverflowError error caught");
      out.println("Iterations=" + A.getIteration());
    }
  }

  private static void recursiveMethod() {
    iteration++;
    recursiveMethod();
  }

  private static void showMenu() throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(200);
    StringBuilder b = new StringBuilder();
    b.append("\n\nPress 1 for OOM error : Java heap size\n");
    b.append("Press 2 for OOM error : Java heap size (without collections)\n");
    b.append("Press 3 for OOM error : Metaspace\n");
    b.append("Press 4 for SO error \n");
    b.append("Press 5 for SO error (without recursion function)\n");
    b.append("Press another symbol to exit");
    out.println(b);
  }
}
