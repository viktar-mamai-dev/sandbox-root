package course3.week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClusteringSmall {

  private int VERTICES;
  private int[] id;
  // this array is used for balancing trees while merging 2 trees into 1
  private int[] size;

  private final ArrayList<Node> edges = new ArrayList<>();

  public int root(int node) {
    int i = node;
    while (i != id[i]) {
      i = id[i];
    }
    return i;
  }

  public boolean connected(int root1, int root2) {
    return root(root1) == root(root2);
  }

  public void union(int node1, int node2) {
    // System.out.println("Union: " + node1 + " : " + node2);
    int root1 = root(node1);
    int root2 = root(node2);

    if (root1 == root2) return;

    if (size[root1] < size[root2]) {
      id[root1] = root2;
      size[root2] += size[root1];
    } else {
      id[root2] = root1;
      size[root1] += size[root2];
    }
  }

  // 106
  public int algo() {
    final int CLUSTERS_END = 4;
    Collections.sort(edges);

    int clusters = VERTICES;
    int i = 0;
    for (; clusters > CLUSTERS_END; i++) {
      Node edge = edges.get(i);
      int src = edge.src;
      int dest = edge.dest;
      if (!connected(src, dest)) {
        union(src, dest);
        clusters--;
      }
    }

    int spacing = Integer.MAX_VALUE;
    for (; i < edges.size(); i++) {
      Node edge = edges.get(i);
      int src = edge.src;
      int dest = edge.dest;
      if (!connected(src, dest)) {
        spacing = Math.min(spacing, edge.weight);
      }
    }
    System.out.println("Spacing: " + spacing);
    return spacing;
  }

  private void readInput() {
    try (Scanner scanner = new Scanner(new File("src/main/resources/course3/clustering_big.txt"))) {
      VERTICES = scanner.nextInt();
      id = new int[VERTICES];
      size = new int[VERTICES];
      for (int i = 0; i < VERTICES; i++) {
        id[i] = i;
        size[i] = 1;
      }

      while (scanner.hasNextInt()) {
        int src = scanner.nextInt();
        int dest = scanner.nextInt();
        int weight = scanner.nextInt();
        edges.add(new Node(src - 1, dest - 1, weight));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
