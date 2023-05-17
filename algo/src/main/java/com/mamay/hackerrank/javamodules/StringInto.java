package com.mamay.hackerrank.javamodules;

import java.util.Scanner;
import java.util.Stack;

public class StringInto {

  public static void main(String[] args) throws Exception {
    MyQueue<Integer> queue = new MyQueue<Integer>();

    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();

    for (int i = 0; i < n; i++) {
      int operation = scan.nextInt();
      if (operation == 1) { // enqueue
        queue.enqueue(scan.nextInt());
      } else if (operation == 2) { // dequeue
        queue.dequeue();
      } else if (operation == 3) { // print/peek
        System.out.println(queue.peek());
      }
    }
    scan.close();
  }
} // end of Solution

class MyQueue<T> {

  private Stack<T> mainStack = new Stack<>();
  private Stack<T> supStack = new Stack<>();

  public void enqueue(T i) {
    mainStack.push(i);
  }

  public void dequeue() {
    if (!supStack.isEmpty()) {
      supStack.pop();
    }
    while (!mainStack.isEmpty()) {
      supStack.push(mainStack.pop());
    }
    supStack.pop();
  }

  public T peek() {
    return mainStack.get(0);
  }
}
