package com.mamay.hackerrank.practice.graph;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Prim {

  static int minKey(int key[], Boolean mstSet[]) {
    int min = Integer.MAX_VALUE, minIdx = -1;

    int n = key.length;
    for (int v = 0; v < n; v++)
      if (!mstSet[v] && key[v] < min) {
        min = key[v];
        minIdx = v;
      }

    return minIdx;
  }

  static int prims(int n, int[][] edges, int start) {
    int key[] = new int[n];
    int parent[] = new int[n];
    Boolean mstSet[] = new Boolean[n];
    for (int i = 0; i < n; i++) {
      key[i] = Integer.MAX_VALUE;
      mstSet[i] = false;
    }
    key[start - 1] = 0;
    parent[start - 1] = -1;

    int graph[][] = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(graph[i], 0);
    }

    for (int[] edge : edges) {
      graph[edge[0] - 1][edge[1] - 1] = edge[2];
      graph[edge[1] - 1][edge[0] - 1] = edge[2];
    }

    for (int count = 0; count < n - 1; count++) {
      // Pick thd minimum key vertex from the set of vertices not yet included in MST
      int u = minKey(key, mstSet);
      mstSet[u] = true;

      // Update key value of the adjacent vertices of the picked vertex. Consider only those
      // vertices which are not yet included in MST
      for (int v = 0; v < n; v++)

        // graph[u][v] is non zero only for adjacent vertices of m
        // mstSet[v] is false for vertices not yet included in MST
        // Update the key only if graph[u][v] is smaller than key[v]
        if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
          parent[v] = u;
          key[v] = graph[u][v];
        }
    }

    int sum = 0;
    for (int i = 0; i < n; i++) if (parent[i] != -1) sum += graph[parent[i]][i];
    return sum;
  }

  private static int[][] readMatrixWithSize(Scanner scanner, int size) {
    int res[][] = new int[size][];
    for (int k = 0; k < size; k++) {
      String tokens[] = scanner.nextLine().replaceAll("]|\\[,", "").split(" ");
      int len2 = tokens.length;
      res[k] = new int[len2];
      for (int m = 0; m < len2; m++) {
        res[k][m] = Integer.parseInt(tokens[m]);
      }
    }
    System.out.println("Size of matrix is: " + size);
    return res;
  }

  public static void main(String[] args) throws IOException {
    Path inputPath = Paths.get("hackerrank", "src", "main", "resources", "input1.txt");
    Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
    try (Scanner scanner = new Scanner(inputPath)) {
      int n = scanner.nextInt();
      int m = scanner.nextInt();
      scanner.nextLine();

      int g[][] = readMatrixWithSize(scanner, m);
      int start = scanner.nextInt();

      System.out.println(prims(n, g, start));
    }
  }
}
