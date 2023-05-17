package com.mamay.hackerrank.practice.sorting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FindMedian {
  private static void merge(int[] a, int low, int mid, int high) {
    int i = low;
    int j = mid + 1;
    int n = high - low + 1;
    int[] aux = new int[n];
    for (int k = 0; k < n; k++) {
      if (i > mid) {
        aux[k] = a[j++];
      } else if (j > high) {
        aux[k] = a[i++];
      } else if (a[j] < a[i]) {
        aux[k] = a[j++];
      } else {
        aux[k] = a[i++];
      }
    }
    System.arraycopy(aux, 0, a, low, n);
  }

  private static void mergeSort(int[] a, int low, int high) {
    if (low >= high) {
      return;
    }

    int mid = (low + high) / 2;
    mergeSort(a, low, mid);
    mergeSort(a, mid + 1, high);
    merge(a, low, mid, high);
  }

  public static void mergeSort(int[] a) {
    int n = a.length;
    mergeSort(a, 0, n - 1);
  }

  // Complete the findMedian function below.
  static int findMedian(int[] arr) {
    mergeSort(arr);
    int len = arr.length;
    return arr[len / 2];
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] arr = new int[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arrItem = Integer.parseInt(arrItems[i]);
      arr[i] = arrItem;
    }

    int result = findMedian(arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
