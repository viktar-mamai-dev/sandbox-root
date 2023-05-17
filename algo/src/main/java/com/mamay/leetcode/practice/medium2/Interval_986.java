package com.mamay.leetcode.practice.medium2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interval_986 {
    List<Integer> startingPoint = new ArrayList<>();
    List<Integer> finishingPoints = new ArrayList<>();

    public int[][] intervalIntersection(int[][] A, int[][] B) {

        int n1 = A.length, n2 = B.length;

        int i1 = 0, i2 = 0;
        while (i1 < n1 && i2 < n2) {
            if (A[i1][0] > B[i2][1]) {
                i2++;
                continue;
            }
            if (A[i1][1] < B[i2][0]) {
                i1++;
                continue;
            }
            s(A, B, n1, n2, i1, i2);
            if (A[i1][1] > B[i2][1])
                i2++;
            else
                i1++;
        }

        int n = startingPoint.size();
        int[][] res = new int[n][2];
        for (int i = 0; i < n; i++) {
            res[i][0] = startingPoint.get(i);
            res[i][1] = finishingPoints.get(i);
        }
        return res;
    }

    private void s(int[][] A, int[][] B, int n1, int n2, int i1, int i2) {
        if (i1 >= n1 || i2 >= n2)
            return;

        int s1, f1;
        if (B[i2][0] < A[i1][0]) {
            if (B[i2][1] < A[i1][1]) {
                s1 = A[i1][0];
                f1 = B[i2][1];
            } else {
                s1 = A[i1][0];
                f1 = A[i1][1];
            }
        } else {
            if (B[i2][1] < A[i1][1]) {
                s1 = B[i2][0];
                f1 = B[i2][1];
            } else {
                s1 = B[i2][0];
                f1 = A[i1][1];
            }
        }
        startingPoint.add(s1);
        finishingPoints.add(f1);
    }

    private static int[][] readMatrixOneLined(Scanner scanner) {
        String lines[] = scanner.nextLine().split("(],)");
        int len = lines.length;
        int res[][] = new int[len][];

        for (int k = 0; k < len; k++) {
            String tokens[] = lines[k].replaceAll("]|\\[", "").split(",");
            int len2 = tokens.length;
            res[k] = new int[len2];
            for (int m = 0; m < len2; m++) {
                res[k][m] = Integer.parseInt(tokens[m]);
            }
        }
        System.out.println("Size of matrix is: " + len + " * " + res[0].length);
        return res;
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        Interval_986 main = new Interval_986();
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    int[][] arr1 = readMatrixOneLined(scanner);
                    int[][] arr2 = readMatrixOneLined(scanner);
                    writer.println(Arrays.deepToString(main.intervalIntersection(arr1, arr2)));
                }
            }
        }
    }
}
