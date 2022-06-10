package course3.week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Prima {
    private int vertexSize;
    private int edgeSize;

    private int[] keys;
    private boolean[] usedInMst;
    private int[][] graph; //Adjacency List
    private int[] parent;

    public static void main(String[] args) {
        Prima main = new Prima();
        main.readInput();
        main.primMST();
    }

    private int minKey() {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 1; v <= vertexSize; v++)
            if (!usedInMst[v] && keys[v] < min) {
                min = keys[v];
                minIndex = v;
            }

        return minIndex;
    }

    private void printMST() {
        long total = 0;
        for (int i = 1; i <= vertexSize; i++) {
            if (parent[i] != -1) {
                total += graph[parent[i]][i];
            }
        }
        System.out.println("MST weight : " + total);
    }

    // -3612829
    private void primMST() {
        Arrays.fill(keys, Integer.MAX_VALUE);
        Arrays.fill(usedInMst, false);

        final int startIndex = 1;
        keys[startIndex] = 0;     // Make key 0 so that this vertex is
        parent[startIndex] = -1; // First node is always root of MST

        for (int count = 1; count <= vertexSize; count++) {
            int u = minKey();
            usedInMst[u] = true;

            for (int v = 1; v <= vertexSize; v++)
                if (graph[u][v] != 0 && !usedInMst[v] && graph[u][v] < keys[v]) {
                    parent[v] = u;
                    keys[v] = graph[u][v];
                }
        }
        printMST();
    }

    private void readInput() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/course3/edges.txt"))) {
            vertexSize = scanner.nextInt();
            edgeSize = scanner.nextInt();

            graph = new int[vertexSize + 1][vertexSize + 1];
            keys = new int[vertexSize + 1];
            usedInMst = new boolean[vertexSize + 1];
            parent = new int[vertexSize + 1];

            while (scanner.hasNextInt()) {
                int src = scanner.nextInt();
                int dest = scanner.nextInt();
                int weight = scanner.nextInt();
                graph[src][dest] = weight;
                graph[dest][src] = weight;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
