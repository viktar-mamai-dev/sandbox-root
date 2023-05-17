package com.mamay.leetcode.practice.medium2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class ShortestPath_1730 {
    boolean used[][];
    int m, n;
    int cache[][];

    public int getFood(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        used = new boolean[m][n];
        cache = new int[m][n];
        int currX = 0, currY = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    currX = i;
                    currY = j;
                }
            }
            Arrays.fill(used[i], false);
            Arrays.fill(cache[i], 0);
        }
        return solve(grid, currX, currY);
    }

    private int solve(char[][] grid, int x, int y) {
        if (grid[x][y] == '#') return 0;
        if (used[x][y]) return cache[x][y] != 0 ? cache[x][y] : -1;
        used[x][y] = true;
        int result = -1;
        int d[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 4; i++) {
            if (isValid(grid, x + d[i][0], y + d[i][1])) {
                int subResult = solve(grid, x + d[i][0], y + d[i][1]);
                if (subResult != -1 && (subResult < result || result == -1)) result = subResult + 1;
            }
        }
        cache[x][y] = result;
        return result;
    }

    boolean isValid(char[][] grid, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 'X';
    }

    public static void main(String[] args) throws Exception {
        Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        ShortestPath_1730 main = new ShortestPath_1730();
        try (Scanner scanner = new Scanner(inputPath)) {
            char[][] grid = readCharMatrix(scanner);
            System.out.println(main.getFood(grid));
        }
    }

    private static char[][] readCharMatrix(Scanner scanner) {
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        char res[][] = new char[m][n];
        for (int k = 0; k < m; k++) {
            String tokens[] = scanner.nextLine().replaceAll("\"", "").replaceAll("\\[|\\]", "").split(",");
            for (int i = 0; i < n; i++) {
                res[k][i] = tokens[i].charAt(0);
            }
        }
        System.out.println("Size of matrix is: " + m + " * " + n);
        return res;
    }
}
