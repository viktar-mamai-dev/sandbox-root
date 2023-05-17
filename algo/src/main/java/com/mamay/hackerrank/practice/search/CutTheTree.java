package com.mamay.hackerrank.practice.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {
  // static long path[];
  static boolean visited[];
  static long subtotal = Integer.MAX_VALUE;
  static long total = 0;
  static long res = Integer.MAX_VALUE;

  public static int cutTheTree(List<Integer> data, List<List<Integer>> adjMatrix) {
    int n = data.size();
    int totalSum = 0;
    int subtree[] = new int[n];

    visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      subtree[i] = data.get(i);
      totalSum += data.get(i);
      visited[i] = false;
    }

    dfs(0, -1, totalSum, adjMatrix, subtree);
    return (int) res;
  }

  private static void dfs(
      int u, int parent, int totalSum, List<List<Integer>> adjMatrix, int subtree[]) {
    int sum = subtree[u];
    visited[u] = true;
    for (int v : adjMatrix.get(u)) {
      if (!visited[v] && v != parent) {
        dfs(v, u, totalSum, adjMatrix, subtree);
        sum += subtree[v];
      }
      if (-totalSum + 2 * sum >= res) break;
    }

    subtree[u] = sum;
    if (u != 0 && Math.abs(totalSum - 2 * sum) < res) res = Math.abs(totalSum - 2 * sum);
  }
}

public class CutTheTree {
  public static void main(String[] args) throws IOException {
    Path inputPath = Paths.get("hackerrank", "src", "main", "resources", "input1.txt");
    Path outputPath = Paths.get("hackerrank", "src", "main", "resources", "output.txt");

    try (BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(new FileInputStream(inputPath.toFile())));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath.toFile()))) {

      int n = Integer.parseInt(bufferedReader.readLine().trim());

      List<Integer> data =
          Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
              .map(Integer::parseInt)
              .collect(Collectors.toList());

      List<List<Integer>> edges = new ArrayList<>();
      List<List<Integer>> adjMatrix = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        adjMatrix.add(new ArrayList<>());
      }

      IntStream.range(0, n - 1)
          .forEach(
              i -> {
                try {
                  String[] split = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                  int v1 = Integer.parseInt(split[0]) - 1;
                  int v2 = Integer.parseInt(split[1]) - 1;
                  adjMatrix.get(v1).add(v2);
                  adjMatrix.get(v2).add(v1);
                  edges.add(Arrays.asList(v1, v2));
                } catch (IOException ex) {
                  throw new RuntimeException(ex);
                }
              });

      int result = Result.cutTheTree(data, adjMatrix);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();
    }
  }
}
