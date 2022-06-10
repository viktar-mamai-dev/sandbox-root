package com.mamay.leetcode.practice.easy2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Relative_1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int idx = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int k = idx; k < arr1.length; k++) {
                if (arr1[k] == arr2[i]) {
                    if (k != idx) {
                        int temp = arr1[idx];
                        arr1[idx] = arr1[k];
                        arr1[k] = temp;
                    }
                    idx++;
                }
            }
        }
        for (int i = idx; i < arr1.length; i++) {
            int minIdx = i;
            for (int k = i + 1; k < arr1.length; k++) {
                if (arr1[k] < arr1[minIdx]) {
                    minIdx = k;
                }

            }
            if (minIdx != i) {
                int temp = arr1[i];
                arr1[i] = arr1[minIdx];
                arr1[minIdx] = temp;
            }
        }
        return arr1;
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        Relative_1122 main = new Relative_1122();
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    int arr1[] = readIntegerArray(scanner);
                    int arr2[] = readIntegerArray(scanner);
                    writer.println(Arrays.toString(main.relativeSortArray(arr1, arr2)));
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
