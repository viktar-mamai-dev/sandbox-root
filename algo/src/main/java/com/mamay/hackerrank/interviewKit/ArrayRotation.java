package com.mamay.hackerrank.interviewKit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ArrayRotation {
    // Complete the rotLeft function below.
    // Complete the minimumMoves function below.
    static int minimumMoves(String[] strGrid, int startX, int startY, int goalX, int goalY) {
        ArrayDeque<Integer> xDeque = new ArrayDeque<>();
        ArrayDeque<Integer> yDeque = new ArrayDeque<>();

        int n = strGrid.length;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(grid[i], -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (strGrid[i].charAt(j) == 'X') {
                    grid[i][j] = -2;
                }
            }
        }

        if (startX == goalX && startY == goalY) return 0;
        grid[startX][startY] = 0;
        xDeque.add(startX);
        yDeque.add(startY);
        while (!xDeque.isEmpty()) {
            int currentX = xDeque.pollFirst();
            int currentY = yDeque.pollFirst();
            int val = grid[currentX][currentY];

            for (int i = currentX + 1; i < n && grid[i][currentY] != -2; i++) {
                if (grid[i][currentY] < 0) {
                    grid[i][currentY] = val + 1;
                    xDeque.add(i);
                    yDeque.add(currentY);
                }
                if (i == goalX && currentY == goalY) return grid[i][currentY];
            }

            for (int i = currentX - 1; i >= 0 && grid[i][currentY] != -2; i--) {
                if (grid[i][currentY] < 0) {
                    grid[i][currentY] = val + 1;
                    xDeque.add(i);
                    yDeque.add(currentY);
                }
                if (i == goalX && currentY == goalY) return grid[i][currentY];
            }

            for (int j = currentY + 1; j < n && grid[currentX][j] != -2; j++) {
                if (grid[currentX][j] < 0) {
                    grid[currentX][j] = val + 1;
                    xDeque.add(currentX);
                    yDeque.add(j);
                }
                if (currentX == goalX && j == goalY) return grid[currentX][j];
            }

            for (int j = currentY - 1; j >= 0 && grid[currentX][j] != -2; j--) {
                if (grid[currentX][j] < 0) {
                    grid[currentX][j] = val + 1;
                    xDeque.add(currentX);
                    yDeque.add(j);
                }
                if (currentX == goalX && j == goalY) return grid[currentX][j];
            }
        }
        return -3;
    }

    // Complete the isValid function below.
    static String isValid(String s) {
        int frequencies[] = new int[26];
        Arrays.fill(frequencies, 0);
        for (char ch : s.toCharArray()) {
            frequencies[ch - 'a']++;
        }

        HashMap<Integer, Integer> freqOfFreq = new HashMap<>();
        //int firstFreq = 0;
        for (int fr : frequencies) {
            if (fr <= 0)
                continue;

            Integer val = freqOfFreq.get(fr);
            if (val == null) {
                val = 0;
            }
            freqOfFreq.put(fr, ++val);
            if (freqOfFreq.size() > 2) {
                return "NO";
            }
        }

        if (freqOfFreq.size() == 1) {
            return "YES";
        }

        Map.Entry<Integer, Integer>[] objects = freqOfFreq.entrySet().toArray(new Map.Entry[freqOfFreq.size()]);
        if (objects[0].getValue() == 1 && objects[0].getKey() == 1) return "YES";
        if (objects[1].getValue() == 1 && objects[1].getKey() == 1) return "YES";

        if (Math.abs(objects[0].getKey() - objects[1].getKey()) != 1) return "NO";
        if (objects[0].getValue() != 1 && objects[1].getValue() != 1) return "NO";

        return "YES";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
