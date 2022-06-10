package com.mamay.leetcode.practice.easy1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class RomanToInteger_13 {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        char chArr[] = s.toCharArray();
        int len = chArr.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int val1 = map.get(chArr[i]);
            int val = val1;
            if (i < len - 1) {
                int val2 = map.get(chArr[i + 1]);
                if (val2 > val1) {
                    val = val2 - val1;
                    i++;
                }
            }
            sum += val;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        RomanToInteger_13 main = new RomanToInteger_13();
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {
                String s = scanner.nextLine();

                writer.println(main.romanToInt(s));
            }
            scanner.close();
        }
    }
}
