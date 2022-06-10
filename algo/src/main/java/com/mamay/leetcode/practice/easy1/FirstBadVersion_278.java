package com.mamay.leetcode.practice.easy1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FirstBadVersion_278 {
    private static int badVersion;

    static boolean isBadVersion(int version) {
        return version >= badVersion;
    }

    public static int firstBadVersion(int n) {
        long left = 1, right = n;

        long mid = (left + right) / 2;
        while (left < right) {
            if (isBadVersion((int) mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return (int) right;
    }

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {

                badVersion = scanner.nextInt();
                int n = scanner.nextInt();

                writer.println(firstBadVersion(n));
            }
            scanner.close();
        }
    }
}
