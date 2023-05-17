package com.mamay.leetcode.contest.weeks_80_90.week86;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Keys {

  public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int len = rooms.size();
    boolean used[] = new boolean[len];
    int usedSize = 0;

    if (len == 1) return true;

    used[usedSize++] = true;
    ArrayDeque<Integer> deque = new ArrayDeque<>();
    deque.add(0);

    while (!deque.isEmpty()) {
      Integer next = deque.pollFirst();
      for (Integer room : rooms.get(next)) {
        if (!used[room]) {
          used[room] = true;
          usedSize++;
          deque.add(room);
        }
      }
    }

    return usedSize == len;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {

        List<List<Integer>> superList = readListList(scanner);

        writer.println(canVisitAllRooms(superList));
      }
      scanner.close();
    }
  }

  private static List<List<Integer>> readListList(Scanner scanner) {
    List<String> linesList = new ArrayList<String>();
    while (scanner.hasNextLine()) {
      linesList.add(scanner.nextLine());
    }
    int len = linesList.size();
    List<List<Integer>> superList = new ArrayList<>(len);

    for (int k = 0; k < len; k++) {
      String replacesString = linesList.get(k).replaceAll("\\]|\\[", "");
      if (replacesString == null || replacesString.trim().isEmpty()) {
        superList.add(new ArrayList<>());
        continue;
      }

      String tokens[] = replacesString.split(",");
      List<Integer> rooms =
          Arrays.stream(tokens).map(token -> Integer.parseInt(token)).collect(Collectors.toList());
      superList.add(rooms);
    }
    System.out.println("Size of SUPER list is: " + len);
    return superList;
  }
}
