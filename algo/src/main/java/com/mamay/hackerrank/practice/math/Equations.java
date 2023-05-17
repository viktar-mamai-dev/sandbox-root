package com.mamay.hackerrank.practice.math;

import java.util.ArrayList;

public class Equations {
  private static final int MODULO = 1000007;

  static int solve(int n) {
    sieve(n); // create sieve
    long result = 1;

    for (Integer primeNum : allPrimes) {
      long p = primeNum;
      long exp = 0;
      while (p <= n) {
        exp = exp + (n / p);
        p = p * primeNum;
      }
      result = (result * (2 * exp + 1)) % MODULO;
    }

    // return total divisors
    return (int) result;
  }

  private static ArrayList<Integer> allPrimes = new ArrayList<>();

  // Fills above vector allPrimes[] for a given n
  static void sieve(int n) {
    boolean[] prime = new boolean[n + 1];

    for (int i = 0; i <= n; i++) prime[i] = true;

    for (int p = 2; p * p <= n; p++) {
      if (prime[p]) {
        for (int i = p * 2; i <= n; i += p) prime[i] = false;
      }
    }
    for (int p = 2; p <= n; p++) if (prime[p]) allPrimes.add(p);
  }

  // Function to find all result of factorial number
  static long factorialDivisors(int n) {
    sieve(n); // create sieve
    long result = 1;

    // find exponents of all primes which divides nand less than n
    for (Integer primeNum : allPrimes) {
      long p = primeNum;
      long exp = 0;
      while (p <= n) {
        exp = exp + (n / p);
        p = p * primeNum;
      }
      result = result * (2 * exp + 1);
    }

    // return total divisors
    return result;
  }
}
