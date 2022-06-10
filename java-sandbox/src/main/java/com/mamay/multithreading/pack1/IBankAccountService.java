package com.mamay.multithreading.pack1;

public interface IBankAccountService {
    void deposit(long amount);

    void withdraw(long amount);

    long getBalance();
}
