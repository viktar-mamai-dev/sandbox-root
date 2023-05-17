package course3.week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffMan {
    private final PriorityQueue<Node> queue = new PriorityQueue<>();

    private Node root;

    public static void main(String[] args) {
        HuffMan main = new HuffMan();
        main.readInput();
        main.algo();
    }

    private void algo() {
        while (queue.size() > 1) {
            Node x = queue.poll();
            Node y = queue.poll();
            Node z = new Node(x, y);

            root = z;
            queue.add(z);
        }

        System.out.println("Min depth: " + root.minDepth); // 9
        System.out.println("Max depth: " + root.maxDepth); // 19
    }

    private void readInput() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/course3/huffman.txt"))) {
            int n = scanner.nextInt();

            while (scanner.hasNextInt()) {
                int weight = scanner.nextInt();
                queue.add(new Node(weight));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class Node implements Comparable<Node> {
        private Integer weight;
        private int minDepth = 0;
        private int maxDepth = 0;

        private Node left;
        private Node right;

        Node(Integer weight) {
            this.weight = weight;
        }

        Node(Node leftNode, Node rightNode) {
            this.left = leftNode;
            this.right = rightNode;
            this.weight = left.weight + right.weight;
            this.maxDepth = Math.max(left.maxDepth, right.maxDepth) + 1;
            this.minDepth = Math.min(left.minDepth, right.minDepth) + 1;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
