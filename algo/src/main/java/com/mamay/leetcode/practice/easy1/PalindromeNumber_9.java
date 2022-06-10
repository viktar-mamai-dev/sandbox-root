package com.mamay.leetcode.practice.easy1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PalindromeNumber_9 {
    public static boolean isPalindrome(int x) {
        String xStr = String.valueOf(x);
        StringBuilder builder = new StringBuilder(xStr);
        String revStr = builder.reverse().toString();
        return revStr.equals(xStr);
    }

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {

                int x = scanner.nextInt();

                writer.println(isPalindrome(x));
            }
            scanner.close();
        }
    }
}
