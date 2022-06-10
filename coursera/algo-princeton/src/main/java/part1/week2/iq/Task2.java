package part1.week2.iq;

import java.util.Stack;

/**
 * Create a data structure that efficiently supports the stack operations (push and pop) and
 * also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them
 * */
public class Task2<T extends Comparable<T>> extends Stack<T>{
    private Stack<T> maximumStack = new Stack<T>();

    @Override
    public T push(T item) {
        super.push(item);
        pushToMaxStack(item);
        return item;
    }

    private void pushToMaxStack(T item) {
        if (maximumStack.isEmpty()) {
            maximumStack.push(item);
        } else {
            T currentMaximum = maximumStack.peek();
            T maxItem = item.compareTo(currentMaximum) > 0 ? item : currentMaximum;
            maximumStack.push(maxItem);
        }
    }

    @Override
    public synchronized T pop() {
        T item = super.pop();
        maximumStack.pop();
        return item;
    }

    public T getMax() {
        return maximumStack.peek();
    }
}
