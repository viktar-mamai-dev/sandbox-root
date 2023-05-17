package com.mamay.leetcode.contest.weeks_90_100.week90;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Score {

  public static int scoreOfParentheses(String S) {
    char chArr[] = S.toCharArray();
    ArrayDeque<Integer> array = new ArrayDeque<>();
    for (char ch : chArr) {
      if (ch == '(') array.push(0);
      if (ch == ')') {
        int prev = array.pop();
        if (prev == 0) {
          int value = 1;
          if (!array.isEmpty() && array.peek() != 0) {
            value += array.pop();
          }
          array.push(value);
        } else {
          prev *= 2;
          array.pop(); // skip 0
          if (!array.isEmpty() && array.peek() != 0) {
            prev += array.pop();
          }
          array.push(prev);
        }
      }
    }
    return array.pop();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();
    System.out.println(scoreOfParentheses(str));
    scanner.close();
  }
}
