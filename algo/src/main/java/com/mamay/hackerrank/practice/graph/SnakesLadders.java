package com.mamay.hackerrank.practice.graph;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SnakesLadders {

    private static final int MAGIC_NUMBER = 101;
    private static Node[] graph = new Node[MAGIC_NUMBER];

    // Complete the quickestWayUp function below.
    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        for (int i = 1; i < MAGIC_NUMBER; i++) {
            graph[i] = new Node(i);
        }
        for (int[] ladder : ladders) {
            graph[ladder[0]].next = new Node(ladder[1]);
        }

        for (int[] ladder : snakes) {
            graph[ladder[0]].next = new Node(ladder[1]);
        }

        int res = solve(1);
        if (res >= MAGIC_NUMBER) return -1;
        return res;
    }

    private static int solve(int rootNumber) {
        if (rootNumber > 100) return MAGIC_NUMBER;
        if (rootNumber == 100) return 0;
        Node root = graph[rootNumber];
        if (root.used) {
            return root.min;
        }

        root.used = true;

        if (root.next != null) {
            root.min = solve(root.next.number);
            return root.min;
        }

        root.min = solve(rootNumber + 6);
        for (int i = 5; i >= 1; i--) {
            root.min = Math.min(root.min, solve(rootNumber + i));
        }


        return ++root.min;
    }

    static class Node {
        int number;
        int min = MAGIC_NUMBER;
        boolean used = false;

        Node next;

        Node(int number) {
            this.number = number;
        }
    }

    public static void main(String[] args) throws Exception {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        try (Scanner scanner = new Scanner(inputPath)) {
            int q = scanner.nextInt();

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
                for (int qi = 0; qi < q; qi++) {
                    int laddersCount = scanner.nextInt();
                    int ladders[][] = new int[laddersCount][2];
                    for (int i = 0; i < laddersCount; i++) {
                        ladders[i][0] = scanner.nextInt();
                        ladders[i][1] = scanner.nextInt();
                    }
                    int snakesCount = scanner.nextInt();
                    int snakes[][] = new int[snakesCount][2];
                    for (int i = 0; i < snakesCount; i++) {
                        snakes[i][0] = scanner.nextInt();
                        snakes[i][1] = scanner.nextInt();
                    }
                    writer.println(quickestWayUp(ladders, snakes));
                }
            }
        }
    }
}
