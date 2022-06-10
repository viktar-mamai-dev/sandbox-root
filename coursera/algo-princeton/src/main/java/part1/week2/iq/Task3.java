package part1.week2.iq;

import java.lang.reflect.Array;

/**
 * Generic array creation
 */
public class Task3<T> {

    private T[] array;

    public void createArrayCheckedType(Class<T> clazz, int n) {
        array = (T[]) Array.newInstance(clazz, n);
    }

    public void createArrayUncheckedType(int n) {
        array = (T[]) new Object[n];
    }

    public T get(int index) {
        return array[index];
    }

    public void put(int index, T value) {
        array[index] = value;
    }
}
