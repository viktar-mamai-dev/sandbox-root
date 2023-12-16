package com.mamay.multithreading.jmp2022;

import java.util.List;

public class ThreadUtil {

  public static void start(List<Thread> threads) {
    for (Thread thread : threads) {
      thread.start();
    }
  }

  /**
   * wait all threads to complete
   *
   * @param threads
   */
  public static void join(List<Thread> threads) {
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void sleep(long interval) {
    try {
      // Wait for some time to demonstrate threads
      Thread.sleep(interval);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
