package com.mamay.leetcode.practice.easy2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Sort_1356 {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        int bits[] = new int[n];
        int res[] = new int[n];
        for (int k = 0; k < n; k++) {
            bits[k] = numberOfOnes(arr[k]);
            res[k] = arr[k];
        }

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int k = i + 1; k < n; k++) {
                if (bits[k] < bits[minIdx]) {
                    minIdx = k;
                } else if (bits[k] == bits[minIdx] && res[k] < res[minIdx]) {
                    minIdx = k;
                }

            }
            if (minIdx != i) {
                swap(res, minIdx, i);
                swap(bits, minIdx, i);
            }
        }
        return res;
    }

    private void swap(int arr[], int i, int k) {
        int tmp = arr[i];
        arr[i] = arr[k];
        arr[k] = tmp;
    }

    private int numberOfOnes(int value) {
        int count = 0;
        while (value != 0) {
            if (value % 2 == 1)
                count += 1;
            value /= 2;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        Sort_1356 main = new Sort_1356();
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                String[] list = new String[q];
                for (int qi = 0; qi < q; qi++) {
                    int nums1[] = readIntegerArray(scanner);
                    writer.println(Arrays.toString(main.sortByBits(nums1)));
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
