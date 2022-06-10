package com.mamay.leetcode.contest.weeks_100_110.week104;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class XofaKind {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    private static boolean solve(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        if (n <= 1) {
            return false;
        }

        ArrayList<Integer> frequences = new ArrayList<>();
        int value = a[0];
        int i = 0;
        while (i < n) {
            i++;
            int count = 1;
            while (i < n && a[i] == value) {
                i++;
                count++;
            }
            if (count == 1) {
                return false;
            }
            frequences.add(count);
            if (i >= n) {
                break;
            }
            value = a[i];
        }
        int gcd = frequences.get(0);
        for (int k = 1; k < frequences.size(); k++) {
            gcd = gcd(gcd, frequences.get(k));
            if (gcd == 1) {
                return false;
            }
        }
        return gcd >= 2;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {

                int matrix[] = readIntegerArray(scanner);

                writer.println(solve(matrix));
            }
            scanner.close();
        }
    }

    private static int[] readIntegerArray(Scanner scanner) {
        String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
        int len = tokens.length;
        int res[] = new int[len];
        for (int k = 0; k < len; k++) {
            res[k] = Integer.parseInt(tokens[k]);
        }
        System.out.println("Length of array is: " + len);
        return res;
    }
}
