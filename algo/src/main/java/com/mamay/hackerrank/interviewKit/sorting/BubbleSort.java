package com.mamay.hackerrank.interviewKit.sorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BubbleSort {
    // Complete the countSwaps function below.
    private static int swaps = 0;

    static void countSwaps(int[] a) {
        swaps = 0;
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }

        try (PrintWriter w = new PrintWriter(new FileWriter(new File("output_hackerrank.txt")))) {

            w.printf("Array is sorted in %d swaps.\n", swaps);
            w.println("First Element: " + a[0]);
            w.println("Last Element: " + a[n - 1]);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        swaps++;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        countSwaps(a);

        scanner.close();
    }
}
