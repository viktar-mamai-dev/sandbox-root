package com.mamay.leetcode.practice.easy1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReverseInteger_7 {

    public static int reverse(int x) {
        boolean minus = false;
        if (x == 0 || x == Integer.MIN_VALUE)
            return 0;
        if (x < 0) {
            minus = true;
            x = -x;
        }
        StringBuilder builder = new StringBuilder();
        String xStr = String.valueOf(x);
        String reversedStr = builder.append(xStr).reverse().toString();
        long newVal = Long.parseLong(reversedStr);
        if (newVal > Integer.MAX_VALUE) {
            return 0;
        }
        if (minus) {
            newVal = -newVal;
        }
        return (int) newVal;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int q = scanner.nextInt();
        scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(new File("output.txt")))) {
            for (int qi = 0; qi < q; qi++) {

                int val = scanner.nextInt();

                writer.println(reverse(val));
            }
            scanner.close();
        }
    }
}
