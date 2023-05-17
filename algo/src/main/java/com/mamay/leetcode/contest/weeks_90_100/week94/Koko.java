package com.mamay.leetcode.contest.weeks_90_100.week94;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Koko {

  public static int solve(int[] arr, int h) {
    long totalSum = 0;
    for (int i = 0; i < arr.length; i++) {
      totalSum += arr[i];
    }

    int start = (int) Math.ceil((double) totalSum / h);
    int K = 0;
    for (int r = start; K == 0; r++) {
      int candidate = 0;
      for (int p : arr) {
        candidate += Math.ceil((double) p / r);
      }
      if (candidate <= h) K = r;
    }
    return K;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File("input.txt"));
    String[] line = scanner.nextLine().split(", ");
    int N = line.length;
    int commands[] = new int[N];
    for (int i = 0; i < N; i++) {
      commands[i] = Integer.parseInt(line[i]);
    }
    int h = scanner.nextInt();
    System.out.println(solve(commands, h));
    scanner.close();
  }
}
