package com.mamay.leetcode.contest.weeks_80_90.week86;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplitArray {

  public static List<Integer> splitIntoFibonacci(String S) {
    int len = S.length();
    for (int i = 1; i <= len / 2; i++) {
      if (S.startsWith("0") && i > 1) return new ArrayList<>();
      Long prevPrevLong = Long.parseLong(S.substring(0, i));
      if (prevPrevLong > Integer.MAX_VALUE) return new ArrayList<>();
      Integer prevPrev = prevPrevLong.intValue();
      for (int k = 1; k <= len / 2; k++) {
        String subString = S.substring(i, i + k);
        if (subString.startsWith("0") && k > 1) return new ArrayList<>();
        Long prevLong = Long.parseLong(subString);
        if (prevLong > Integer.MAX_VALUE) return new ArrayList<>();
        Integer prev = prevLong.intValue();
        boolean res = fib(prevPrev, prev, i + k, S);
        if (res) {
          List<Integer> resultList = new ArrayList<>();
          resultList.add(prevPrev);
          resultList.add(prev);
          int totalLen = i + k;
          while (totalLen < len) {
            Integer next = prev + prevPrev;
            resultList.add(next);
            totalLen += String.valueOf(next).length();
            prevPrev = prev;
            prev = next;
          }
          return resultList;
        }
      }
    }
    return new ArrayList<>();
  }

  private static boolean fib(int prevPrev, int prev, int nextIndex, String str) {
    String nextStr = String.valueOf(prevPrev + prev);
    if (str.substring(nextIndex).startsWith(nextStr)) {
      nextIndex += nextStr.length();
      if (nextIndex == str.length()) {
        return true;
      }
      return fib(prev, Integer.parseInt(nextStr), nextIndex, str);
    }
    return false;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {

        String str = scanner.nextLine();

        writer.println(splitIntoFibonacci(str));
      }
      scanner.close();
    }
  }
}
