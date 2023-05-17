package com.mamay.leetcode.contest.weeks_100_110.week102;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Fruit {

  public int totalFruit(int[] tree) {
    int firstNumStartPos = 0, firstNumEndPos = 0, secondNumStartPos = 0, secondNumEndPos = 0;
    Integer firstNum = null, secondNum = null;
    int maxCount = 0;
    int n = tree.length;
    for (int i = 0; i < n; i++) {
      if (firstNum == null) {
        firstNum = tree[i];
        firstNumStartPos = i;
      }

      if (tree[i] == firstNum) {
        firstNumEndPos = i;
      } else {
        if (secondNum == null) {
          secondNum = tree[i];
          secondNumStartPos = i;
        }
        if (tree[i] == secondNum) {
          secondNumEndPos = i;
        } else {
          maxCount =
              Math.max(
                  maxCount,
                  Math.max(secondNumEndPos - firstNumStartPos, firstNumEndPos - firstNumStartPos)
                      + 1);

          if (firstNumEndPos > secondNumEndPos) {
            firstNumStartPos = secondNumEndPos + 1;
          } else {
            firstNumStartPos = firstNumEndPos + 1;
            firstNumEndPos = secondNumEndPos;
            firstNum = secondNum;
          }
          // остается
          secondNum = tree[i];
          secondNumStartPos = i;
          secondNumEndPos = i;
        }
      }
    }

    maxCount =
        Math.max(
            maxCount,
            Math.max(secondNumEndPos - firstNumStartPos, firstNumEndPos - firstNumStartPos) + 1);

    return maxCount;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    Fruit m = new Fruit();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        // TODO
        int array[] = readIntegerArray(scanner);
        writer.println(m.totalFruit(array));
      }
      scanner.close();
    }
  }

  private static int[] readIntegerArray(Scanner scanner) {
    String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
    int len = tokens.length;
    int res[] = new int[len];
    for (int k = 0; k < len; k++) {
      res[k] = Integer.parseInt(tokens[k]);
    }
    System.out.println("Length of array is: " + len);
    return res;
  }
}
