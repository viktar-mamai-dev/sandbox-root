package course3.week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WeightedIndependentSet {
    private int n;
    private int[] weightes;
    private ArrayList<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        WeightedIndependentSet main = new WeightedIndependentSet();
        main.readInput();
        main.algo();
        main.check(1, 2, 3, 4, 17, 117, 517, 997);
    }

    private void algo() {
        long[] a = new long[n + 1];
        a[0] = 0;
        a[1] = weightes[0];
        for (int i = 2; i <= n; i++) {
            a[i] = Math.max(a[i - 1], a[i - 2] + weightes[i - 1]);
        }
        int i = n;
        while (i >= 2) {
            if (a[i - 1] >= a[i - 2] + weightes[i - 1]) {
                i--;
            } else {
                path.add(i);
                i -= 2;

            }
        }
        if (i == 1) {
            path.add(i);
        }

    }

    private void check(int... vertexes) {
        for (int v : vertexes) {
            System.out.println("Vertex " + v + " is in WIS : " + path.contains(v));
        }
    }

    private void readInput() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/course3/mwis.txt"))) {
            n = scanner.nextInt();
            weightes = new int[n];

            int i = 0;
            while (scanner.hasNextInt()) {
                weightes[i++] = scanner.nextInt();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
