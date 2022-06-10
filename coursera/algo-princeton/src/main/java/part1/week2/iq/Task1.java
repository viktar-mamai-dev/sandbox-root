package part1.week2.iq;

import java.util.Stack;

/**
 * Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations
 */
public class Task1 <T> {
    private Stack<T> inboxStack = new Stack<T>();
    private Stack<T> outboxStack = new Stack<T>();

    public void enqueue(T item) {
        inboxStack.push(item);
    }

    public T dequeue() {
        if (!outboxStack.isEmpty()) {
            return outboxStack.pop();
        }

        while (!inboxStack.isEmpty()) {
            outboxStack.push(inboxStack.pop());
        }
        return outboxStack.pop();
    }
}
