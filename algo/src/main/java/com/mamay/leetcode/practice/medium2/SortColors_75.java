package com.mamay.leetcode.practice.medium2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SortColors_75 {
  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public void sortColors(int[] nums) {
    int n = nums.length;
    int pointer = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        swap(nums, pointer, i);
        pointer++;
      }
    }
    int border = pointer;
    pointer = n - 1;
    for (int i = n - 1; i >= border; i--) {
      if (nums[i] == 2) {
        swap(nums, pointer, i);
        pointer--;
      }
    }
  }

  private void swap(int[] nums, int pointer, int i) {
    int tmp = nums[pointer];
    nums[pointer] = nums[i];
    nums[i] = tmp;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    SortColors_75 main = new SortColors_75();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        int matrix[] = readIntegerArray(scanner);

        main.sortColors(matrix);
        writer.println(Arrays.toString(matrix));
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
