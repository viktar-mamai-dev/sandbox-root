package course2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Median {
    private final PriorityQueue<Integer> maxQueue;
    private final PriorityQueue<Integer> minQueue;

    private int n;

    private Median() {
        maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        minQueue = new PriorityQueue<>();
        n = 0;
    }

    public static void main(String[] args) {
        Median main = new Median();

        try (Scanner scanner = new Scanner(new File("src/main/resources/median.txt"))) {
            int i = 1;
            double sum = 0;
            while (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                main.insert(value);
                double median = main.getMedian();
                sum += median;
                sum %= 10000;
                System.out.println("Median " + i + " : " + median);
                i++;
            }
            System.out.println("Sum " + sum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void insert(int x) {
        maxQueue.add(x);
        if (n % 2 == 0) {
            if (minQueue.isEmpty()) {
                n++;
                return;
            } else if (maxQueue.peek() > minQueue.peek()) {
                int maxRoot = maxQueue.poll();
                int minRoot = minQueue.poll();
                maxQueue.add(minRoot);
                minQueue.add(maxRoot);
            }
        } else {
            minQueue.add(maxQueue.poll());
        }
        n++;
    }

    private double getMedian() {
        return (double) maxQueue.peek();
    }
}
