/*
 * Copyright (c) 2023
 */
package com.mamay.multithreading.pack1;

import java.util.Random;

public class BankAccountService implements IBankAccountService {

  private volatile long balance;

  BankAccountService(long balance) {
    this.balance = balance;
  }

  public static void main(String[] args) throws InterruptedException {
    BankAccountService b1 = new BankAccountService(3450);
    BankAccountService b2 = new BankAccountService(6550);

    Random rand = new Random();

    Thread[] tr = new Thread[4];
    for (int i = 0; i <= 3; i++) {
      tr[i] =
          new Thread(
              () -> {
                for (int k = 0; k < 100; k++) {
                  if (rand.nextBoolean()) {
                    int bal = rand.nextInt((int) b1.getBalance() / 5);
                    b1.withdraw(bal);
                    b2.deposit(bal);
                  } else {
                    int bal = rand.nextInt((int) b2.getBalance() / 5);
                    b2.withdraw(bal);
                    b1.deposit(bal);
                  }
                  try {
                    Thread.sleep(100);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              });
      tr[i].start();
    }
    for (int i = 0; i <= 3; i++) {
      tr[i].join();
    }
    System.out.println("Balance 1 " + b1.getBalance() + " \nBalance 2 " + b2.getBalance());
  }

  @Override
  public synchronized void deposit(long amount) {
    balance += amount;
  }

  @Override
  public synchronized void withdraw(long amount) {
    balance -= amount;
  }

  @Override
  public long getBalance() {
    return balance;
  }
}
