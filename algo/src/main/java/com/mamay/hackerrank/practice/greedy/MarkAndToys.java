package com.mamay.hackerrank.practice.greedy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MarkAndToys {

  // Complete the maximumToys function below.
  static int maximumToys(int[] prices, int k) {
    Arrays.sort(prices);
    int n = prices.length;
    boolean makePurchase = true;
    int i = 0;
    int sum = 0;
    while (makePurchase) {
      if (sum + prices[i] <= k) {
        sum += prices[i];
        i++;
      } else {
        makePurchase = false;
      }
      if (i >= n) {
        makePurchase = false;
      }
    }

    return i;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

    String[] nk = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nk[0]);

    int k = Integer.parseInt(nk[1]);

    int[] prices = new int[n];

    String[] pricesItems = scanner.nextLine().split(" ");
    // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int pricesItem = Integer.parseInt(pricesItems[i]);
      prices[i] = pricesItem;
    }

    int result = maximumToys(prices, k);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
