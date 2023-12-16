package com.mamay.leetcode.practice.medium3;

import java.util.ArrayList;
import java.util.List;

public class Generate_22 {

  private List<String> resList = new ArrayList<>();
  private int n;

  public List<String> generateParenthesis(int n) {
    if (n == 0) {
      return resList;
    }

    this.n = n;
    char[] array = new char[2 * n];
    array[0] = '(';
    rec(array, 1, 1, 0);
    return resList;
  }

  public void rec(char[] array, int size, int openSize, int closedSize) {
    if (size == 2 * n) {
      resList.add(new String(array));
      return;
    }

    if (openSize < n) {
      array[size] = '(';
      rec(array, size + 1, openSize + 1, closedSize);
    }

    if (closedSize < openSize) {
      array[size] = ')';
      rec(array, size + 1, openSize + 1, closedSize);
    }
  }
}
