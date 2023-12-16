package com.mamay.leetcode.practice.easy4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Next_496 {
  public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;

    HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
    for (int k = 0; k < n; k++) {
      map1.put(nums2[k], k);
    }

    int res[] = new int[m];
    for (int i = 0; i < m; i++) {
      res[i] = -1;
      if (map1.containsKey(nums1[i])) {
        for (int k = map1.get(nums1[i]); k < n; k++) {
          if (nums2[k] > nums1[i]) {
            res[i] = nums2[k];
            break;
          }
        }
      }
    }
    return res;
  }

  public static void main(String[] args) throws IOException {
    Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
    Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
    try (Scanner scanner = new Scanner(inputPath)) {
      int q = scanner.nextInt();
      scanner.nextLine();

      try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
        String[] list = new String[q];
        for (int qi = 0; qi < q; qi++) {
          int nums1[] = readIntegerArray(scanner);
          int nums2[] = readIntegerArray(scanner);
          writer.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
        }
      }
    }
  }

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
}
