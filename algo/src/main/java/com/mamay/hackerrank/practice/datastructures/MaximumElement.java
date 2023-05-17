package com.mamay.hackerrank.practice.datastructures;

import java.util.Scanner;
import java.util.Stack;

public class MaximumElement {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();

    scan.nextLine();

    Stack<Integer> mainStack = new Stack<>();
    Stack<Integer> maxStack = new Stack<>();

    for (int i = 0; i < n; i++) {
      String[] strArray = scan.nextLine().split(" ");
      if (strArray[0].equals("1")) {
        int value = Integer.parseInt(strArray[1]);
        mainStack.push(value);
        int max = value;
        if (!maxStack.isEmpty()) {
          max = Math.max(max, maxStack.peek());
        }
        maxStack.push(max);
      } else if (strArray[0].equals("2")) {
        mainStack.pop();
        maxStack.pop();
      } else if (strArray[0].equals("3")) {
        System.out.println(maxStack.peek());
      }
    }
  }
}
