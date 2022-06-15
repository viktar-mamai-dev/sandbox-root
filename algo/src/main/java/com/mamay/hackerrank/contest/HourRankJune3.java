package com.mamay.hackerrank.contest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HourRankJune3 {

    private static final Scanner scanner = new Scanner(System.in);
    static int totalGlobal = 0;
    static int q;
    static Node[] nodes;
    static int[] b;

    private static Integer[] sum(Integer[] a, Integer[] d) {
        Integer[] sum = new Integer[10];
        for (int i = 0; i < 10; i++) {
            sum[i] = a[i] + d[i];
        }
        return sum;
    }

    private static boolean check(Integer[] a, Integer[] d, int q) {
        for (int i = 0; i < 10; i++) {
            if (a[i] + d[i] > q) {
                return false;
            }
        }
        return true;
    }

    static int maximumElegance(int q, String[] s, int[] b) {
        int n = s.length;
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, s[i], b[i]);
        }

        boolean[] used = new boolean[n];
        Arrays.fill(used, false);
        for (int i = 0; i < n; i++) {
            used[i] = true;
            bfs(b[i], i, used, nodes[i].countOfNumbers);
            used[i] = false;
        }

        return totalGlobal;
    }

    static void bfs(int totalCurrent, int index, boolean[] used, Integer[] countOfNumbersCurrent) {
        int[] copy = new int[10];

        for (int k = 0; k < used.length; k++) {
            if (used[k] == false) {
                if (check(countOfNumbersCurrent, nodes[k].countOfNumbers, q)) {
                    countOfNumbersCurrent = sum(countOfNumbersCurrent, nodes[k].countOfNumbers);
                    used[k] = true;
                    System.arraycopy(countOfNumbersCurrent, 0, copy, 0, 10);
                    int copyTotal = totalCurrent;
                    int total = totalCurrent + (b[index] ^ b[k]);
                    if (total > totalGlobal) {
                        totalGlobal = total;
                    }
                    bfs(total, k, used, countOfNumbersCurrent);

                    used[k] = false;
                    total = copyTotal;
                    System.arraycopy(copy, 0, countOfNumbersCurrent, 0, 10);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nq = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nq[0]);

        q = Integer.parseInt(nq[1]);

        b = new int[n];

        String[] bItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int bItem = Integer.parseInt(bItems[i]);
            b[i] = bItem;
        }

        String[] s = new String[n];

        for (int i = 0; i < n; i++) {
            String sItem = scanner.nextLine();
            s[i] = sItem;
        }

        int result = maximumElegance(q, s, b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    public static class Node {
        public int index;
        /* count of numbers of 0 to 9 in a given string */
        public Integer[] countOfNumbers = new Integer[10];
        public int beauty;

        Node(int index, String s, int b) {
            this.index = index;
            this.beauty = b;
            char[] arr = s.toCharArray();
            for (char ch : arr) {
                countOfNumbers[ch]++;
            }

            String[] a = s.split("\\B"); // splits the string on non word boundaries, producing an array of strings each
            // has one digit
            countOfNumbers = Arrays.stream(a).map(str -> Integer.valueOf(str)).collect(Collectors.toList())
                    .toArray(new Integer[10]);
        }
    }

    public static class Edge {
        // not setted
        public int beauty;
        public int[] plusCountOfNumbers = new int[10];

        public Edge(int i, int[] sum) {
            this.beauty = i;
            this.plusCountOfNumbers = sum;
        }
    }
}
