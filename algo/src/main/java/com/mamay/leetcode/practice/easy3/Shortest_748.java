package com.mamay.leetcode.practice.easy3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shortest_748 {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int minValue = Integer.MAX_VALUE;
        ArrayList<Character> containee = new ArrayList<>();
        for (char ch : licensePlate.toCharArray()) {
            if (Character.isLetter(ch)) {
                containee.add(ch);
            }
        }
        HashMap<Character, Integer> bMap = charsToCharacterMap(containee);
        String res = "";
        for (String word : words) {
            if (contains(charsToCharacterMap(word.toCharArray()), bMap)) {
                if (word.length() < minValue) {
                    minValue = word.length();
                    res = word;
                }
            }
        }
        return res;
    }

    public HashMap<Character, Integer> charsToCharacterMap(char[] arr) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            c = Character.toLowerCase(c);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public HashMap<Character, Integer> charsToCharacterMap(ArrayList<Character> arr) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            c = Character.toLowerCase(c);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public boolean contains(HashMap<Character, Integer> a, HashMap<Character, Integer> b) {
        for (Map.Entry<Character, Integer> entry : b.entrySet()) {
            if (!a.containsKey(entry.getKey()) || a.get(entry.getKey()) < entry.getValue())
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        Shortest_748 main = new Shortest_748();
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();
            scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    String line = scanner.nextLine();
                    String arr2[] = readStringArray(scanner);
                    writer.println(main.shortestCompletingWord(line, arr2));
                }
            }
        }
    }

    private static String[] readStringArray(Scanner scanner) {
        String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
        int len = tokens.length;
        System.out.println("Length of array is: " + len);
        return tokens;
    }
}
