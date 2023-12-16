package com.mamay.leetcode.practice.medium2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IntegerToRoman_12 {

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public String intToRoman(int num) {
    StringBuilder builder = new StringBuilder();
    char b[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    char a[] = {'C', 'X', 'I'};
    while (num >= 1_000) {
      builder.append(b[0]);
      num -= 1000;
    }
    int x = 100;
    int idx = 0;
    while (num > 0) {
      int digit = num / x;
      if (digit == 9) {
        builder.append(a[idx]).append(b[2 * idx]);
      } else if (digit == 4) {
        builder.append(a[idx]).append(b[2 * idx + 1]);
      } else if (digit >= 5) {
        builder.append(b[2 * idx + 1]);
      }
      int rem = digit % 5;
      while (rem >= 1 && rem <= 3) {
        builder.append(b[2 * idx + 2]);
        rem--;
      }
      idx++;
      num -= digit * x;
      x /= 10;
    }

    return builder.toString();
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    IntegerToRoman_12 main = new IntegerToRoman_12();
    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        int val = scanner.nextInt();

        writer.println(main.intToRoman(val));
      }
      scanner.close();
    }
  }
}
