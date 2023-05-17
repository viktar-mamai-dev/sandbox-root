package com.mamay.leetcode.practice.easy1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TwoSum_1 {

  public static int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int len = nums.length;
    for (int i = 0; i < len; i++) {
      int elem = nums[i];
      Integer idx = map.get(elem);
      if (idx != null) {
        return new int[] {idx, i};
      }
      map.put(target - elem, i);
    }

    return null;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File("input.txt"));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File("output.txt")))) {
      for (int qi = 0; qi < q; qi++) {

        int matrix[] = readIntegerArray(scanner);
        int target = scanner.nextInt();
        scanner.nextLine();

        writer.println(Arrays.toString(twoSum(matrix, target)));
      }
      scanner.close();
    }
  }

  private static int[] readIntegerArray(Scanner scanner) {
    String tokens[] = scanner.nextLine().split(",");
    int len = tokens.length;
    System.out.println("Length of array is: " + len);
    int res[] = new int[len];
    for (int k = 0; k < len; k++) {
      res[k] = Integer.parseInt(tokens[k].trim());
    }
    return res;
  }
}
