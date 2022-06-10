package practice;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BorderIterator {
    public static void main(String[] args) {
        Iterator<Integer> iterator = Arrays.asList(1, 3, 5, 7, 9, 12, 17).iterator();
        print(m(iterator, 18));
    }

    private static <T extends Number & Comparable<T>> Iterator<T> m(Iterator<T> iter1, T num) {

        Iterator<T> iter2 = new Iterator<T>() {

            T next;
            boolean hasNext;

            @Override
            public boolean hasNext() {
                hasNext = true;
                if (!iter1.hasNext()) {
                    hasNext = false;
                    return false;
                }

                try {
                    next = iter1.next();
                } catch (NoSuchElementException e) {
                    hasNext = false;
                    return false;
                }

                if (next.compareTo(num) > 0) {
                    hasNext = false;
                    return false;
                }


                return true;

            }

            @Override
            public T next() {
                if (!hasNext) {
                    throw new NoSuchElementException();
                }

                return next;
            }
        };
        return iter2;
    }

    private static <T extends Number & Comparable<T>> void print(Iterator<T> iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + "  ");
        }
        System.out.println("=====END=====");
    }
}
