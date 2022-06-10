package com.mamay.hackerrank.practice.math;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JognGcd {
    // Complete the solve function below.
    static int[] solve(int[] a) {
        int n = a.length;
        int b[] = new int[n + 1];
        b[0] = a[0];
        b[n] = a[n - 1];
        for (int i = 1; i < n; i++) {
            int gcd = gcd(a[i - 1], a[i]);
            b[i] = (a[i - 1] * a[i]) / gcd;
        }
        return b;
    }

    static int gcd(int b, int c) {
        while (b != 0) {
            int temp = b;
            b = c % b;
            c = temp;
        }
        return c;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int aCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] a = new int[aCount];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int aItr = 0; aItr < aCount; aItr++) {
                int aItem = Integer.parseInt(aItems[aItr]);
                a[aItr] = aItem;
            }

            int[] result = solve(a);

            for (int resultItr = 0; resultItr < result.length; resultItr++) {
                bufferedWriter.write(String.valueOf(result[resultItr]));

                if (resultItr != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

}
