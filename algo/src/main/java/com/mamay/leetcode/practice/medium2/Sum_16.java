package com.mamay.leetcode.practice.medium2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sum_16 {

  public int threeSum(int[] nums, int sum) {
    List<List<Integer>> resList = new ArrayList<>();

    Arrays.sort(nums);
    int diff = Integer.MAX_VALUE;
    int resSum = 0;
    for (int i = 0; i < nums.length - 2; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        int sum2 = nums[i] + nums[j] + nums[k];
        if (sum2 == sum) {
          return sum;
        } else if (sum2 < sum) {
          j++;
          int diff2 = sum - sum2;
          if (diff2 < diff) {
            diff = diff2;
            resSum = sum2;
          }
        } else {
          k--;
          int diff2 = sum2 - sum;
          if (diff2 < diff) {
            diff = diff2;
            resSum = sum2;
          }
        }
      }
    }
    return resSum;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  private static int[] readIntegerArray(Scanner scanner) {
    String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
    int len = tokens.length;
    int res[] = new int[len];
    for (int k = 0; k < len; k++) {
      res[k] = Integer.parseInt(tokens[k].trim());
    }
    System.out.println("Length of array is: " + len);
    return res;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    Sum_16 main = new Sum_16();
    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        int matrix[] = readIntegerArray(scanner);
        int target = scanner.nextInt();
        scanner.nextLine();

        writer.println(main.threeSum(matrix, target));
      }
      scanner.close();
    }
  }
}
