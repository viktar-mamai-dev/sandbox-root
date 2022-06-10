package course3.week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class KnapSack {
    private int n;
    private int w;

    private int[] weightes;
    private int[] values;
    private int[][] a;

    public static void main(String[] args) {
        KnapSack main = new KnapSack();
        main.readInput();
        main.algoForBig();
    }

    // 2493893
    private void algo() {
        a = new int[n + 1][w + 1];
        Arrays.fill(a[0], 0);
        for (int i = 1; i <= n; i++) {
            for (int x = 0; x <= w; x++) {
                a[i][x] = getValue(i, x);
            }
        }

        System.out.println("Optimal solution : " + a[n][w]); // 2493893
    }

    private void algoForBig() {
        a = new int[2][w + 1];
        Arrays.fill(a[0], 0);
        int m = 0;
        for (int i = 1; i <= n; i++) {
            for (int x = 0; x <= w; x++) {
                m = i % 2;
                a[m][x] = getValueForBig(i, x, m);
            }
        }

        System.out.println("Optimal solution : " + a[m][w]); //
    }

    private int getValue(int i, int x) {
        if (x < weightes[i]) {
            return a[i - 1][x];
        }
        return Math.max(a[i - 1][x], a[i - 1][x - weightes[i]] + values[i]);
    }

    private int getValueForBig(int i, int x, int m) {
        if (x < weightes[i]) {
            return a[1 - m][x];
        }
        return Math.max(a[1 - m][x], a[1 - m][x - weightes[i]] + values[i]);
    }

    private void readInput() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/course3/knapsack_big.txt"))) {
            w = scanner.nextInt();
            n = scanner.nextInt();
            weightes = new int[n + 1];
            values = new int[n + 1];

            int i = 1;
            while (scanner.hasNextInt()) {
                values[i] = scanner.nextInt();
                weightes[i] = scanner.nextInt();
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
