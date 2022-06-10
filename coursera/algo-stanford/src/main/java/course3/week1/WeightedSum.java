package course3.week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class WeightedSum {
    private Node[] arr;

    public static void main(String[] args) {
        WeightedSum main = new WeightedSum();
        main.readInput();
        main.algoByDifference();
        main.algoByRatio();
    }

    // 69119377652
    private void algoByDifference() {
        Arrays.sort(arr, new DiffComparator());
        int totalLength = 0;
        long sum = 0;
        for (Node anArr : arr) {
            totalLength += anArr.length;
            sum += totalLength * anArr.weight;
        }
        System.out.println("Total Sum (algo by diff)  " + sum);
    }

    // 67311454237
    private void algoByRatio() {
        Arrays.sort(arr, new RatioComparator());
        int totalLength = 0;
        long sum = 0;
        for (Node anArr : arr) {
            totalLength += anArr.length;
            sum += totalLength * anArr.weight;
        }
        System.out.println("Total Sum (algo by ratio) " + sum);
    }

    private void readInput() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/course3/jobs.txt"))) {
            int len = scanner.nextInt();
            arr = new Node[len];
            int i = 0;
            while (scanner.hasNextInt()) {
                int w = scanner.nextInt();
                int l = scanner.nextInt();
                arr[i] = new Node((short) w, (short) l);
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class Node {
        private final short weight;
        private final short length;

        private Node(short weight, short length) {
            this.weight = weight;
            this.length = length;
        }
    }

    private class DiffComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            int diff1 = o1.weight - o1.length;
            int diff2 = o2.weight - o2.length;

            if (diff1 != diff2) {
                return diff2 - diff1;
            }

            return o2.weight - o1.weight;
        }
    }

    private class RatioComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            double ratio1 = ((double) o1.weight) / o1.length;
            double ratio2 = ((double) o2.weight / o2.length);

            if (ratio1 != ratio2) {
                return Double.compare(ratio2, ratio1);
            }

            return o2.weight - o1.weight;
        }
    }
}
