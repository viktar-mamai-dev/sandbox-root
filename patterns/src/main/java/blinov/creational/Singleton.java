/*
 * Copyright (c) 2023
 */
package blinov.creational;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Singleton {
  private static Singleton instance = null;
  private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);
  private static final Lock lock = new ReentrantLock();

  private Singleton() {}

  public static Singleton getInstance() {
    if (!instanceCreated.get()) {
      lock.lock();
      try {
        if (!instanceCreated.get()) {
          instance = new Singleton();
          instanceCreated.set(true);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
    return instance;
  }
}
