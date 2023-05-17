package com.mamay.hackerrank.practice.warmup;

import java.util.Scanner;

public class AlmostSorted {

  // Complete the almostSorted function below.
  static void almostSorted(int[] arr) {
    int len = arr.length;
    int i = 0;
    while (i < len - 1 && arr[i] < arr[i + 1]) {
      i++;
    }
    String resultStr = postfixSorted(i, len, arr);
    System.out.println(resultStr);
  }

  static String postfixSorted(int i, int len, int[] arr) {
    if (i >= len - 1) {
      return "yes";
    }
    int j = len - 1;
    while (arr[j] > arr[j - 1]) {
      j--;
    }
    swap(i, j, arr);

    if (isSegmentSorted(i, j, len, arr, true)) {
      if ((i == 0 || (i > 0 && arr[i - 1] < arr[i]))
          && (j == len - 1 || (j < len - 1 && arr[j] < arr[j + 1]))) {
        return "yes\nswap " + (i + 1) + " " + (j + 1);
      }
    }
    swap(i, j, arr);
    if (isSegmentSorted(i, j, len, arr, false)) {
      if ((i == 0 || (i > 0 && arr[i - 1] < arr[j]))
          && (j == len - 1 || (j < len - 1 && arr[i] < arr[j + 1]))) {
        return "yes\nreverse " + (i + 1) + " " + (j + 1);
      }
    }
    return "no";
  }

  static void swap(int i, int j, int[] arr) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  static boolean isSegmentSorted(int start, int end, int len, int[] arr, boolean asc) {
    for (int i = start; i < end; i++) {
      if ((asc && arr[i] >= arr[i + 1]) || (!asc && arr[i] <= arr[i + 1])) {
        return false;
      }
    }
    return true;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int n = scanner.nextInt();
    // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    scanner.nextLine();

    int[] arr = new int[n];

    String[] arrItems = scanner.nextLine().split(" ");
    // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arrItem = Integer.parseInt(arrItems[i]);
      arr[i] = arrItem;
    }
    almostSorted(arr);
    scanner.close();
  }
}
