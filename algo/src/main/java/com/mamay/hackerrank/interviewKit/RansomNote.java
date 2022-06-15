package com.mamay.hackerrank.interviewKit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RansomNote {
    private static final Scanner scanner = new Scanner(System.in);

    // Complete the checkMagazine function below.
    private static void checkMagazine(String[] magazine, String[] note) {
        HashMap<String, Integer> magazinMap = new HashMap<>();
        HashMap<String, Integer> noteMap = new HashMap<>();

        for (String m : magazine) {
            Integer value = magazinMap.get(m);
            if (value == null) {
                value = 0;
            }
            magazinMap.put(m, ++value);
        }

        for (String m : note) {
            Integer value = noteMap.get(m);
            if (value == null) {
                value = 0;
            }
            noteMap.put(m, ++value);
        }

        try (PrintWriter bufferedWriter = new PrintWriter(new FileWriter(new File("output_hackerrank.txt")))) {
            bufferedWriter.write(checkMaps(magazinMap, noteMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String checkMaps(HashMap<String, Integer> magazinMap, HashMap<String, Integer> noteMap) {
        for (Map.Entry<String, Integer> entry : noteMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            Integer magazineValue = magazinMap.get(key);
            if (magazineValue == null || magazineValue < value) {
                return "No";
            }
        }
        return "Yes";
    }

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
