package com.mamay.leetcode.contest.weeks_80_90.week88;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class LoudAndRich {

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static int[] loudAndRich(int[][] richer, int[] quiet) {
    int len = quiet.length;
    ArrayList adjacent[] = new ArrayList[len];
    for (int i = 0; i < len; i++) {
      adjacent[i] = new ArrayList<>();
    }
    for (int rich[] : richer) {
      adjacent[rich[1]].add(rich[0]);
    }
    int res[] = new int[len];
    for (int i = 0; i < len; i++) {
      int k = 0;
      ArrayList<Integer> list = new ArrayList<Integer>();
      list.addAll(adjacent[i]);
      while (k < list.size()) {
        for (Object a : adjacent[list.get(k)]) {
          if (!list.contains(a)) list.add((Integer) a);
        }
        k++;
      }

      HashSet<Integer> set = new HashSet<Integer>();
      set.addAll(list);
      int quietMin = quiet[i];
      int quietIdx = i;
      for (int q : set) {
        if (quiet[q] < quietMin) {
          quietMin = quiet[q];
          quietIdx = q;
        }
      }
      res[i] = quietIdx;
    }
    return res;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        int matrix[][] = readMatrixOneLined(scanner);
        int array[] = readArray(scanner);

        long startTime = System.currentTimeMillis();
        writer.println(Arrays.toString(loudAndRich(matrix, array)));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
      }
      scanner.close();
    }
  }

  private static int[] readArray(Scanner scanner) {
    String tokens[] = scanner.nextLine().replaceAll("]|\\[", "").split(",");
    int len = tokens.length;
    int res[] = new int[len];
    for (int k = 0; k < len; k++) {
      res[k] = Integer.parseInt(tokens[k]);
    }
    System.out.println("Length of array is: " + len);
    return res;
  }

  private static int[][] readMatrix(Scanner scanner) {
    List<String> lines = new ArrayList<>();
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
    int len = lines.size();
    int res[][] = new int[len][];
    for (int k = 0; k < len; k++) {
      String tokens[] = lines.get(k).replaceAll("[|]|,", "").split(" ");
      int len2 = tokens.length;
      res[k] = new int[len2];
      for (int m = 0; m < len2; m++) {
        res[k][m] = Integer.parseInt(tokens[m]);
      }
    }
    System.out.println("Size of matrix is: " + len + " * " + res[0].length);
    return res;
  }

  private static int[][] readMatrixOneLined(Scanner scanner) {
    String lines[] = scanner.nextLine().split("(],)");
    int len = lines.length;
    int res[][] = new int[len][];

    for (int k = 0; k < len; k++) {
      String tokens[] = lines[k].replaceAll("]|\\[", "").split(",");
      int len2 = tokens.length;
      res[k] = new int[len2];
      for (int m = 0; m < len2; m++) {
        res[k][m] = Integer.parseInt(tokens[m]);
      }
    }
    System.out.println("Size of matrix is: " + len + " * " + res[0].length);
    return res;
  }
}
