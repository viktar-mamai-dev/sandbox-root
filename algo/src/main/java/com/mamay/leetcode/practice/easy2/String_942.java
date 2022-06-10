package com.mamay.leetcode.practice.easy2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class String_942 {

    public static int[] diStringMatch(String s) {
        int n = s.length();
        int leftBoarder = 0;
        int rightBoarder = n;
        int min = 0;
        int max = n;

        int[] res = new int[n + 1];
        int idx = 0;
        while (leftBoarder != rightBoarder) {
            if (s.charAt(idx) == 'I') {
                res[idx] = min++;
            } else {
                res[idx] = max--;
            }
            if (++leftBoarder == rightBoarder) {
                break;
            }

            if (s.charAt(n - 1 - idx) == 'I') {
                res[n - idx] = max--;
            } else {
                res[n - idx] = min++;
            }
            rightBoarder--;

            idx++;
        }

        res[leftBoarder] = min;

        return res;
    }

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", INPUT_FILE);
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", OUTPUT_FILE);
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    writer.println(Arrays.toString(diStringMatch(scanner.nextLine())));
                }
            }
        }
    }
}
