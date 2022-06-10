package com.mamay.hackerrank.interviewKit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TwoStrings {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.nextLine();

        try (PrintWriter bufferedWriter = new PrintWriter(new FileWriter(new File("output_hackerrank.txt")))) {
            for (int i = 0; i < n; i++) {
                String aStr = scanner.nextLine();
                String bStr = scanner.nextLine();
                bufferedWriter.println(solve(aStr, bStr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    private static String solve(String aStr, String bStr) {
        final int LEN = 26;
        int[] aMass = new int[LEN];
        int[] bMass = new int[LEN];
        Arrays.fill(aMass, 0);
        Arrays.fill(bMass, 0);

        for (char ch : aStr.toCharArray()) {
            aMass[ch - 'a']++;
        }

        for (char ch : bStr.toCharArray()) {
            bMass[ch - 'a']++;
        }

        for (int i = 0; i < LEN; i++) {
            if (aMass[i] > 0 && bMass[i] > 0) {
                return "YES";
            }
        }
        return "NO";
    }
}
