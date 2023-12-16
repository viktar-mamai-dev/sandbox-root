package course3.week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClusteringBig {

  private int[] id;
  // this array is used for balancing trees while merging 2 trees into 1
  private int[] size;

  private final HashMap<Integer, ArrayList<Integer>> valueToIndexesMap = new HashMap<>();
  private int BITS;

  public static void main(String[] args) {
    ClusteringBig main = new ClusteringBig();
    /* main.readInput();
    main.algo();*/

    main.readInputBig();
    System.out.println(main.algoBig());
  }

  // time out
  private int algoBig() {
    System.out.println("Starting: ");

    int length = BITS + BITS * (BITS - 1) / 2;
    int[] masks = new int[length];

    masks[0] = 1;
    int idx = 1;
    for (int j = 1; j < BITS; j++) {
      masks[idx++] = 1 << j;
    }
    for (int j1 = 1; j1 < BITS; j1++) {
      for (int j2 = 0; j2 < j1; j2++) {
        masks[idx++] = (1 << j1) + (1 << j2);
      }
    }

    System.out.println("Masks generated ");

    for (int mask : masks) {
      for (Integer key1 : valueToIndexesMap.keySet()) {
        int key2 = key1 ^ mask;
        if (valueToIndexesMap.containsKey(key2)) {
          // TODO union all nodes
          Integer abraI = valueToIndexesMap.get(key1).get(0);
          Integer abraJ = valueToIndexesMap.get(key2).get(0);
          union(abraI, abraJ);
        }
      }
    }
    int clustersSize = 0;

    List<Integer> abra =
        valueToIndexesMap.values().stream().map(list -> list.get(0)).collect(Collectors.toList());

    HashSet<Integer> set = new HashSet<>();
    for (int index1 : abra) {
      set.add(root(index1));
    }

    /*for (int i = 0; i < abra.size() - 1; i++) {
        for (int j = i + 1; j < abra.size(); j++) {
            Integer abraI = abra.get(i);
            Integer abraJ = abra.get(j);
            if (!connected(abraI, abraJ)) {
                clustersSize++;
                if (clustersSize % 1000 == 0) {
                    System.out.println(clustersSize);
                }
            }
        }
    }*/

    clustersSize = set.size();
    System.out.println(clustersSize);
    return clustersSize;
  }

  private void readInputBig() {
    try (Scanner scanner = new Scanner(new File("src/main/resources/course3/clustering_big.txt"))) {
      int VERTICES = scanner.nextInt();
      BITS = scanner.nextInt();
      scanner.nextLine();
      id = new int[VERTICES];
      size = new int[VERTICES];
      for (int i = 0; i < VERTICES; i++) {
        id[i] = i;
        size[i] = 1;
      }

      int i = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        line = line.replaceAll(" ", "");
        int value = Integer.parseInt(line, 2);
        ArrayList<Integer> indexes = valueToIndexesMap.get(value);
        if (indexes == null) {
          indexes = new ArrayList<>();
        } else if (indexes.size() > 0) {
          union(i, indexes.get(0));
        }
        indexes.add(i);
        valueToIndexesMap.put(value, indexes);
        i++;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

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
}
