package com.mamay.multithreading.pack1;

import java.util.concurrent.locks.StampedLock;

public class SharedStore {

  private final StampedLock lock = new StampedLock();
  private int sharedData = 0;

  // Writing data
  public void write(int newValue) {
    long stamp = lock.writeLock();
    try {
      sharedData = newValue;
      System.out.println(Thread.currentThread().getName() + " wrote value: " + newValue);
    } finally {
      lock.unlockWrite(stamp);
    }
  }

  // Reading data (optimistic read)
  public int read() {
    long stamp = lock.tryOptimisticRead();
    int currentValue = sharedData;

    System.out.println(Thread.currentThread().getName() + " current value: " + currentValue);

    try {
      // Simulate some delay to increase the chance of a write occurring during the read
      Thread.sleep(1);

      // Validate if the read was consistent
      if (lock.validate(stamp)) {
        System.out.println(
            Thread.currentThread().getName() + " read value: " + currentValue + " optimistically.");
      } else {
        // Fallback to a read lock
        stamp = lock.readLock();
        currentValue = sharedData;
        System.out.println(
            Thread.currentThread().getName()
                + " detected an inconsistent read. Value after fallback: "
                + currentValue);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      lock.unlockRead(stamp);
    }

    return currentValue;
  }

  public static void main(String[] args) {
    SharedStore store = new SharedStore();

    // Creating a thread that will write data
    Thread writer = new Thread(() -> store.write(42), "Writer");

    // Creating a thread that will attempt to read data optimistically
    Thread reader =
        new Thread(
            () -> {
              int value = store.read();
              System.out.println(Thread.currentThread().getName() + " final read value: " + value);
            },
            "Reader");

    // Starting the threads
    reader.start();
    writer.start();

    // Wait for threads to finish
    try {
      reader.join();
      writer.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
