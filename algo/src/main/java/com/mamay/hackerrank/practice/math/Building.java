package com.mamay.hackerrank.practice.math;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Building {
  static int index;
  static String[] res;

  // Complete the solve function below.
  static String[] solve(String s) {
    index = 0;
    int len = s.length();
    int n = (int) (Math.pow(2, len) - 1);
    res = new String[n];
    char[] chArr = s.toCharArray();
    Arrays.sort(chArr);
    func(0, chArr, "");
    return res;
  }

  static void func(int start, char[] chArr, String temp) {
    if (start >= chArr.length || index >= res.length) return;
    String newTemp = temp + chArr[start];
    res[index++] = newTemp;
    func(start + 1, chArr, newTemp);
    func(start + 1, chArr, temp);
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      String s = scanner.nextLine();

      String[] result = solve(s);

      for (int resultItr = 0; resultItr < result.length; resultItr++) {
        bufferedWriter.write(result[resultItr]);

        if (resultItr != result.length - 1) {
          bufferedWriter.write("\n");
        }
      }

      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
