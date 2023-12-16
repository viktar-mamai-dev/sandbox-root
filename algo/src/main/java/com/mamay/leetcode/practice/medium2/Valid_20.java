package com.mamay.leetcode.practice.medium2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class Valid_20 {
  public boolean isValid(String s) {
    HashMap<Character, Character> map = new HashMap<>(3);
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');

    ArrayDeque<Character> deque = new ArrayDeque<>();
    for (char ch : s.toCharArray()) {
      if (map.containsKey(ch)) {
        deque.addLast(ch);
      } else {
        if (deque.isEmpty()) return false;

        Character lastCh = deque.pollLast();
        Character value = map.get(lastCh);
        if (value == null || value != ch) {
          return false;
        }
      }
    }

    return deque.isEmpty();
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    Valid_20 main = new Valid_20();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        String s = scanner.nextLine();

        writer.println(main.isValid(s));
      }
      scanner.close();
    }
  }
}
