package com.mamay.hackerrank.practice.dp;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Nikita {

    private int[][] dp;
    private int n;
    private long[] prefixSum;

    public int arraySplitting(List<Integer> arr) {
        // Write your code here
        n = arr.size();
        dp = new int[n][n + 1];
        prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            prefixSum[i + 1] = prefixSum[i] + arr.get(i);
        }
        if (prefixSum[n] == 0) return n - 1;
        return solve(0, n);
    }

    private int solve(int low, int high) {
        if (low + 1 >= high) return 0;
        long middleValue = (prefixSum[high] + prefixSum[low]);
        if (middleValue % 2 != 0) return 0;
        int idx = Arrays.binarySearch(prefixSum, low + 1, high, middleValue / 2);
        if (idx < 0) {
            dp[low][high] = 0;
        } else {
            dp[low][high] = 1 + Math.max(solve(low, idx), solve(idx, high));
        }
        return dp[low][high];
    }

    private static Path inputPath = Paths.get("hackerrank", "src", "main", "resources", "input1.txt");
    private static Path outputPath = Paths.get("hackerrank", "src", "main", "resources", "output.txt");

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath.toFile())));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath.toFile()));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        Nikita main = new Nikita();
        IntStream.range(0, t).forEach(tItr -> {
            try {
                int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                int result = main.arraySplitting(arr);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
