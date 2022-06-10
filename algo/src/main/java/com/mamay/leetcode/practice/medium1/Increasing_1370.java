package com.mamay.leetcode.practice.medium1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Increasing_1370 {
    public String sortString(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        StringBuilder b = new StringBuilder();
        boolean d = true;
        while (b.length() < n) {
            if (d) {
                Character minCh = 'a';
                --minCh;
                while ((minCh = getNext(++minCh, map)) != null) {
                    b.append(minCh);
                    map.put(minCh, map.get(minCh) - 1);
                }

            } else {
                Character minCh = 'z';
                ++minCh;
                while ((minCh = getPrev(--minCh, map)) != null) {
                    b.append(minCh);
                    map.put(minCh, map.get(minCh) - 1);
                }
            }
            d = !d;
        }
        return b.toString();
    }

    private Character getNext(Character ch, HashMap<Character, Integer> map) {
        for (char ch1 = ch; ch1 <= 'z'; ch1++) {
            Integer val = map.get(ch1);
            if (val != null && val != 0) {
                return ch1;
            }
        }
        return null;
    }

    private Character getPrev(Character ch, HashMap<Character, Integer> map) {
        for (char ch1 = ch; ch1 >= 'a'; ch1--) {
            Integer val = map.get(ch1);
            if (val != null && val != 0) {
                return ch1;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        Increasing_1370 main = new Increasing_1370();
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    String s = scanner.next();
                    writer.println(main.sortString(s));
                }
            }
        }
    }
}
