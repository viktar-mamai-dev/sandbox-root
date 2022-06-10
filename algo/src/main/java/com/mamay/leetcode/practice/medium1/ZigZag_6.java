package com.mamay.leetcode.practice.medium1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ZigZag_6 {

    public String convert(String s, int numRows) {
        if (numRows == 1)
             return s;
        int len = s.length();
        int i = 0;
        int idx = 0;
        int direction = 1;
        ArrayList<Character> list[] = new ArrayList[numRows];
        for (int k = 0; k < numRows; k++) {
            list[k] = new ArrayList<>();
        }
        while (i < len) {
            list[idx].add(s.charAt(i));
            i++;
            if (idx == numRows - 1)
                direction = -1;
            if (idx == 0)
                direction = 1;
            idx += direction;
        }
        StringBuilder b = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            for (char ch : list[k]) {
                b.append(ch);
            }
        }
        return b.toString();
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        ZigZag_6 main = new ZigZag_6();
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    String s = scanner.next();
                    int a = scanner.nextInt();
                    writer.println(main.convert(s, a));
                }
            }
        }
    }

}
