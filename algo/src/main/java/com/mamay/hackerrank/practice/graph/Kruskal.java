package com.mamay.hackerrank.practice.graph;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Kruskal {

    static class Edge implements Comparable<Edge> {
        private int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    static class Subset {
        int parent, rank;
    }

    private static int find(Subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    private static void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }


    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        int edgeCount = gFrom.size();
        Edge[] edges = new Edge[edgeCount];
        for (int i = 0; i < edgeCount; i++) {
            edges[i] = new Edge(gFrom.get(i) - 1, gTo.get(i) - 1, gWeight.get(i));
        }

        Arrays.sort(edges);

        Subset subsets[] = new Subset[gNodes];
        for (int v = 0; v < gNodes; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int i = 0;
        int e = 0;

        int sum = 0;
        while (e < gNodes - 1) {
            Edge nextEdge = edges[i++];
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);
            if (x != y) {
                e++;
                sum += nextEdge.weight;
                union(subsets, x, y);
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("hackerrank", "src", "main", "resources", "input1.txt");
        Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
        try (Scanner scanner = new Scanner(inputPath)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            scanner.nextLine();

            List<Integer> gFrom = new ArrayList<>();
            List<Integer> gTo = new ArrayList<>();
            List<Integer> gWeight = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                String tokens[] = scanner.nextLine().split(" ");
                gFrom.add(Integer.parseInt(tokens[0]));
                gTo.add(Integer.parseInt(tokens[1]));
                gWeight.add(Integer.parseInt(tokens[2]));
            }

            System.out.println(kruskals(n, gFrom, gTo, gWeight));
        }
    }
}
