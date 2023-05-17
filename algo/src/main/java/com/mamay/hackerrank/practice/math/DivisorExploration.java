package com.mamay.hackerrank.practice.math;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DivisorExploration {

  private static final int MODULO = 1000000007;

  public static int sumDivisorsOfDivisors(int n) {

    // Calculating powers of prime factors and storing them in a map mp[].
    HashMap<Integer, Integer> mp = new HashMap<>();
    for (int j = 2; j <= Math.sqrt(n); j++) {
      int count = 0;
      while (n % j == 0) {
        n /= j;
        count++;
      }
      if (count != 0) mp.put(j, count);
    }

    // If n is a prime number
    if (n != 1) mp.put(n, 1);

    // For each prime factor, calculating (p^(a+1)-1)/(p-1) and adding it to answer.
    long ans = 1;

    for (HashMap.Entry<Integer, Integer> entry : mp.entrySet()) {
      int pw = 1;
      long sum = 0;
      for (int i = entry.getValue() + 1; i >= 1; i--) {
        sum += (i * pw);
        pw *= entry.getKey();
      }
      ans = (ans * sum) % MODULO;
    }

    return (int) ans;
  }

  public static int sumDivisorsOfDivisors(HashMap<Integer, Integer> mp) {
    int ans = 1;

    for (HashMap.Entry<Integer, Integer> entry : mp.entrySet()) {
      int pw = 1;
      long sum = 0;
      for (int i = entry.getValue() + 1; i >= 1; i--) {
        sum += (i * pw);
        pw *= entry.getKey();
      }
      ans = (int) ((ans * sum) % MODULO);
    }

    return ans;
  }

  public static int countDivisorsOfDivisors(int m, int a) {
    long ans = 1;

    for (int i = 0; i < m; i++) {
      long num = a + i + 2;
      long sum = num * (num + 1) / 2;
      ans = (ans * sum) % MODULO;
    }

    return (int) ans;
  }

  static ArrayList<Integer> getFirstPrimes(int m) {
    ArrayList<Integer> list = new ArrayList<>();

    int i = 2;
    boolean flag;
    int count = 0;
    while (count < m) {
      flag = true;

      for (int j = 2; j <= i / 2 && flag; ++j) {
        if (i % j == 0) {
          flag = false;
        }
      }

      if (flag) {
        list.add(i);
        count++;
      }
      i++;
    }
    return list;
  }

  public static void main(String[] args) throws Exception {
    Path inputPath = Paths.get("hackerrank", "src", "main", "resources", "input1.txt");
    long startTime = System.currentTimeMillis();
    try (Scanner scanner = new Scanner(inputPath)) {
      int q = scanner.nextInt();
      scanner.nextLine();

      ArrayList<Integer> mList = new ArrayList<>();
      ArrayList<Integer> aList = new ArrayList<>();
      int max = 0;

      for (int qi = 0; qi < q; qi++) {
        int m = scanner.nextInt();
        int a = scanner.nextInt();
        mList.add(m);
        aList.add(a);
        max = Math.max(m, max);
      }

      // ArrayList<Integer> list = getFirstPrimes(max);

      for (int qi = 0; qi < q; qi++) {
        int m = mList.get(qi);
        int a = aList.get(qi);

        /*HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(list.get(i), a + i + 1);
        }
        System.out.print("Sum : " + sumDivisorsOfDivisors(map));*/
        System.out.println(" Count: " + countDivisorsOfDivisors(m, a));
      }
    }

    System.out.println("Time: " + (System.currentTimeMillis() - startTime));
  }
}
