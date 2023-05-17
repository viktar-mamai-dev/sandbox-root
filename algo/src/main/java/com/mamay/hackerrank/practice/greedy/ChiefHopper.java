package com.mamay.hackerrank.practice.greedy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ChiefHopper {

  // Complete the chiefHopper function below.
  static int chiefHopper(int[] arr) {
    /*
     * int sum = 0; for (int a : arr) { sum += a; } int startEnergy = sum /
     * arr.length; boolean flag = isAchiveable(arr, startEnergy); int res =
     * startEnergy; if (flag) { while (flag) { flag = isAchiveable(arr,
     * --startEnergy); } res = startEnergy + 1; } else { while (!flag) { flag =
     * isAchiveable(arr, ++startEnergy); } res = startEnergy; } return res;
     */

    double sum = 0;
    int maxValues = 0;
    final double MAX_VALUE = Math.pow(2, 30);
    double currentMult = 1;

    int n = arr.length;
    for (int i = 0; i < n; i++) {
      double val = arr[i];
      for (int m = 0; m < maxValues; m++) {
        val /= MAX_VALUE;
      }
      currentMult *= 2;
      val /= currentMult;
      if (currentMult == MAX_VALUE) {
        currentMult = 1;
        maxValues++;
      }
      sum += val;
    }
    System.out.println(sum);
    return (int) Math.ceil(sum);
  }

  static boolean isAchiveable(int arr[], int startEnergy) {
    for (int nextHeight : arr) {
      startEnergy = startEnergy - nextHeight + startEnergy;
      if (startEnergy <= 0) return false;
    }
    return true;
  }

  private static Scanner scanner;

  public static void main(String[] args) throws IOException {
    scanner = new Scanner(new File("input_hackerrank.txt"));
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] arr = new int[n];

    String[] arrItems = scanner.nextLine().split(" ");
    // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arrItem = Integer.parseInt(arrItems[i]);
      arr[i] = arrItem;
    }

    int result = chiefHopper(arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
