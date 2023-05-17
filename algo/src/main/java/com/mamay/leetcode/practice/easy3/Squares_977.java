package com.mamay.leetcode.practice.easy3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Squares_977 {
    public static int[] sortedSquares(int[] A) {

        int n = A.length;
        int absMinIdx = 0;
        int absMinValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (Math.abs(A[i]) < absMinValue) {
                absMinValue = Math.abs(A[i]);
                absMinIdx = i;
            }
        }

        int leftBoarder = absMinIdx - 1;
        int rightBoarder = absMinIdx + 1;

        int[] res = new int[n];
        int idx = 0;
        boolean flag = true;
        for (int i = 0; i < n && flag; i++) {
            res[idx++] = A[absMinIdx] * A[absMinIdx];
            if (leftBoarder < 0) {
                flag = false;
                for (idx = rightBoarder; idx < n; idx++) {
                    res[idx] = A[idx] * A[idx];
                }
            } else if (rightBoarder >= n) {
                flag = false;
                for (idx = leftBoarder; idx >= 0; idx--) {
                    res[n - idx - 1] = A[idx] * A[idx];
                }
            } else {
                if (Math.abs(A[leftBoarder]) < Math.abs(A[rightBoarder])) {
                    absMinIdx = leftBoarder;
                    leftBoarder--;
                } else {
                    absMinIdx = rightBoarder;
                    rightBoarder++;
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
                for (int qi = 0; qi < q; qi++) {
                    writer.println(Arrays.toString(sortedSquares(readIntegerArray(scanner))));
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
