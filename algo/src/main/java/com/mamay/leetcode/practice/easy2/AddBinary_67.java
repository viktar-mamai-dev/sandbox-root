package com.mamay.leetcode.practice.easy2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddBinary_67 {
    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int len = Math.min(aLen, bLen);
        char aChArr[] = a.toCharArray();
        char bChArr[] = b.toCharArray();

        int ov = 0;
        StringBuilder builder = new StringBuilder();
        int i;
        for (i = 0; i < len; i++) {
            int sum = Character.getNumericValue(aChArr[aLen - 1 - i]) + Character.getNumericValue(bChArr[bLen - 1 - i]) + ov;
            if (sum >= 2) {
                sum -= 2;
                ov = 1;
            } else {
                ov = 0;
            }
            builder.append(sum);
        }
        while (i < aLen) {
            int sum = Character.getNumericValue(aChArr[aLen - 1 - i]) + ov;
            if (sum >= 2) {
                sum -= 2;
                ov = 1;
            } else {
                ov = 0;
            }
            builder.append(sum);
            i++;
        }
        while (i < bLen) {
            int sum = Character.getNumericValue(bChArr[bLen - 1 - i]) + ov;
            if (sum >= 2) {
                sum -= 2;
                ov = 1;
            } else {
                ov = 0;
            }
            builder.append(sum);
            i++;
        }
        if (ov == 1) {
            builder.append(1);
        }
        return builder.reverse().toString();
    }

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        AddBinary_67 main = new AddBinary_67();
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {

                String s1 = scanner.nextLine();
                String s2 = scanner.nextLine();

                writer.println(main.addBinary(s1, s2));
            }
            scanner.close();
        }
    }
}
