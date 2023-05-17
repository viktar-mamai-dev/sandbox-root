package com.mamay.hackerrank.practice.math;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Constructing {

  // Complete the canConstruct function below.
  static String canConstruct(int[] a) {
    long sum = 0;
    for (int elem : a) {
      sum += sum(elem);
    }
    return sum % 3 == 0 ? "Yes" : "No";
  }

  static int sum(int a) {
    int sum = 0;
    int d = a;
    while (d != 0) {
      sum += d % 10;
      d /= 10;
    }
    return sum;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] a = new int[n];

      String[] aItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
        int aItem = Integer.parseInt(aItems[i]);
        a[i] = aItem;
      }

      String result = canConstruct(a);

      bufferedWriter.write(result);
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
