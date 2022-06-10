package com.mamay.leetcode.contest.weeks_90_100.week99;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Groups {

    private static int numSpecialEquivGroups(String[] A) {
        // TODO
        return 0;
    }

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {
                String A[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
                System.out.println("Array size: " + A.length);
                writer.println(numSpecialEquivGroups(A));
            }
            scanner.close();
        }
    }
}
