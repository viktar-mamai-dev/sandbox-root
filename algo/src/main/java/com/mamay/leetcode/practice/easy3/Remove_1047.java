package com.mamay.leetcode.practice.easy3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Remove_1047 {
    public static String removeDuplicates(String S) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        deque.push(S.charAt(0));
        for (int i = 1; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (!deque.isEmpty() && deque.peek().equals(ch)) {
                deque.pop();

            } else {
                deque.push(ch);
            }
        }
        StringBuilder b = new StringBuilder();
        while (!deque.isEmpty()) {
            b.append(deque.pop());
        }

        return b.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    writer.println(removeDuplicates(scanner.nextLine()));
                }
            }
        }
    }
}
