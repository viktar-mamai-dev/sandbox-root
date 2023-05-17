package com.mamay.hackerrank.practice.datastructures;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class JesseCookies {
  static int cookies(int k, int[] A) {
    PriorityQueue<Integer> q = new PriorityQueue<>();
    for (int a : A) {
      q.add(a);
    }

    int res = 0;
    while (q.size() > 1 && q.peek() < k) {
      int first = q.poll();
      int second = q.poll();
      q.add(first + 2 * second);
      res++;
    }

    if (q.peek() >= k) {
      return res;
    }
    return -1;
    // return res;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

    String[] nk = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nk[0].trim());

    int k = Integer.parseInt(nk[1].trim());

    int[] A = new int[n];

    String[] AItems = scanner.nextLine().split(" ");

    for (int AItr = 0; AItr < n; AItr++) {
      int AItem = Integer.parseInt(AItems[AItr].trim());
      A[AItr] = AItem;
    }

    int result = cookies(k, A);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();
  }
}
