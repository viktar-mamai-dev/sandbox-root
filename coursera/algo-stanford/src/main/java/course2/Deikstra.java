package course2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Deikstra {

    // 875714
    private final int V = 200;   // No. of vertices
    private int[][] graph;//Adjacency List
    private int[] distances;
    private int[] prevs;
    private boolean[] used;

    public Deikstra() {
        graph = new int[V + 1][V + 1];
        for (int i = 1; i <= V; ++i)
            Arrays.fill(graph[i], 0);
        distances = new int[V + 1];
        prevs = new int[V + 1];
        used = new boolean[V + 1];
    }

    private void deikstra(int s) {
        Arrays.fill(prevs, -1);
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(used, false);

        distances[s] = 0;

        for (int i = 1; i <= V; ++i) {
            int v = -1;
            for (int j = 1; j <= V; ++j)
                if (!used[j] && (v == -1 || distances[j] < distances[v]))
                    v = j;

            if (distances[v] == Integer.MAX_VALUE)
                break;
            used[v] = true;

            for (int j = 1; j < graph.length; j++) {
                if (graph[v][j] == 0) continue;
                int cost = graph[v][j];
                if (distances[v] + cost < distances[j]) {
                    distances[j] = distances[v] + cost;
                    prevs[j] = v;
                }
            }
        }

    }

    private void printDistances(int v) {
        for (int k = 1; k <= V; k++) {
            System.out.println("Length from " + v + " to " + k + " : " + distances[k]);
        }
    }

    public static void main(String[] args) {
        Deikstra main = new Deikstra();
        main.readGraph();
        main.deikstra(1);
        main.printDistances(1);
    }

    private void readGraph() {

        try (Scanner scanner = new Scanner(new File("src/main/resources/dijkstraData.txt"))) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line == null || line.isEmpty()) break;
                String[] tokens = line.split("\\t");
                int v = Integer.parseInt(tokens[0]);
                for (int i = 1; i < tokens.length; i++) {
                    String[] targets = tokens[i].split(",");
                    int w = Integer.parseInt(targets[0]);
                    int wage = Integer.parseInt(targets[1]);
                    graph[v][w] = wage;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
// 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
