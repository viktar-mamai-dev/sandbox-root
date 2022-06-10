package com.mamay.leetcode.practice.easy2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SMth {
    public int minCostToMoveChips(int[] chips) {
        HashMap<Integer, Integer> evenMap = new HashMap<>();
        HashMap<Integer, Integer> oddMap = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int ch : chips) {
            if (ch % 2 == 1) {
                oddMap.putIfAbsent(ch, 0);
                oddMap.merge(ch, 1, Integer::sum);
            } else {
                evenMap.putIfAbsent(ch, 0);
                evenMap.merge(ch, 1, Integer::sum);
            }
        }
        if (evenMap.isEmpty() || oddMap.isEmpty()) return 0;
        for (Map.Entry<Integer, Integer> entry : evenMap.entrySet()) {
            int sum = 0;
            for (Map.Entry<Integer, Integer> entry2 : oddMap.entrySet()) {
                sum += Math.abs(entry2.getKey() - entry.getKey()) * entry2.getValue();
            }
            if (sum < min) min = sum;
        }
        for (Map.Entry<Integer, Integer> entry : oddMap.entrySet()) {
            int sum = 0;
            for (Map.Entry<Integer, Integer> entry2 : evenMap.entrySet()) {
                sum += Math.abs(entry2.getKey() - entry.getKey()) * entry2.getValue();
            }
            if (sum < min) min = sum;
        }
        return min;
    }

    public String[] findWords(String[] words) {
        HashSet<Character> set1 = new HashSet<>();
        set1.add('q');
        set1.add('w');
        set1.add('e');
        set1.add('r');
        set1.add('t');
        set1.add('y');
        set1.add('u');
        set1.add('i');
        set1.add('o');
        set1.add('p');
        HashSet<Character> set2 = new HashSet<>();
        set2.add('a');
        set2.add('s');
        set2.add('d');
        set2.add('f');
        set2.add('g');
        set2.add('h');
        set2.add('j');
        set2.add('k');
        set2.add('l');
        HashSet<Character> set3 = new HashSet<>();
        set3.add('z');
        set3.add('x');
        set3.add('c');
        set3.add('v');
        set3.add('n');
        set3.add('m');
        List<String> resList = new ArrayList<>();
        for (String a : words) {
            HashSet<Character> set4 = new HashSet<Character>();
            for (char ch1 : a.toCharArray()) {
                set4.add(ch1);
            }
            if (set1.containsAll(set4) || set2.containsAll(set4) || set3.containsAll(set4))
                resList.add(a);
        }
        int n = resList.size();
        String[] res = new String[n];
        int idx = 0;
        for (String a : resList) {
            res[idx++] = a;
        }
        return res;

    }

    private static final String INPUT_FILE = "leetcode/src/main/resources/input.txt";
    private static final String OUTPUT_FILE = "leetcode/src/main/resources/output.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        SMth main = new SMth();
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {

                writer.println(main.minCostToMoveChips(readIntegerArray(scanner)));
            }
            scanner.close();
        }
    }

    private static int[] readIntegerArray(Scanner scanner) {
        String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
        int len = tokens.length;
        int res[] = new int[len];
        for (int k = 0; k < len; k++) {
            res[k] = Integer.parseInt(tokens[k].trim());
        }
        System.out.println("Length of array is: " + len);
        return res;
    }
}
