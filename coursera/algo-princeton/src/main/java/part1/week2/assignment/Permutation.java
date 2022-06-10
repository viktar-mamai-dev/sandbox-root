package part1.week2.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt("3");
        RandomizedQueue<String> randQ = new RandomizedQueue<>();

        randQ.enqueue("a");
        randQ.enqueue("b");
        randQ.enqueue("v");

        for (int i = 0; i < k; i++) {
            StdOut.println(randQ.dequeue());
        }

        randQ.enqueue("eg");
        randQ.enqueue("eg");

        StdOut.println(randQ.dequeue());
        StdOut.println(randQ.dequeue());

        Iterator<String> randQiterator = randQ.iterator();
        while (randQiterator.hasNext()) {
            System.out.println(randQiterator.next());
        }
        System.out.println("-=======================");

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        deque.addFirst(9);
        deque.addFirst(10);

        System.out.println("Deque start");
        Iterator<Integer> dequeIterator = deque.iterator();
        while (dequeIterator.hasNext()) {
            System.out.println(dequeIterator.next());
        }
        System.out.println("Deque end");

        RandomizedQueue<String> randQ2 = new RandomizedQueue<>();
        randQ2.enqueue("wf");
        randQ2.enqueue("gfhghg");
        randQ2.enqueue("gfjgj");
        randQ2.enqueue("guytyr");
        randQ2.dequeue();
        System.out.println(randQ2.size());
        randQ2.dequeue();
        randQ2.dequeue();
        randQ2.enqueue("gjrg");
        randQ2.enqueue("grjg");
        randQ2.enqueue("gjrg");
        randQ2.enqueue("gjrg");
        randQ2.enqueue("grjg");
        System.out.println(randQ2.size());
        //deque.addFirst(2);
        //deque.removeFirst();

        Iterator<String> randQ2iterator = randQ2.iterator();
        while (randQ2iterator.hasNext()) {
            System.out.println(randQ2iterator.next());
            //iterator2.remove();
        }
        //iterator2.next();*/
    }
}
