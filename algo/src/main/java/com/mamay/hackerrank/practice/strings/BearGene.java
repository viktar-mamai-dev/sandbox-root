package com.mamay.hackerrank.practice.strings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class BearGene {

  // Complete the steadyGene function below.
  static int steadyGene(String gene) {
    int n = gene.length();
    int m = n / 4;
    char chArr[] = {'A', 'C', 'G', 'T'};
    HashMap<Character, Integer> map = new HashMap<>();
    for (char ch : chArr) {
      map.put(ch, 0);
    }
    int start = n;
    for (int i = 0; i < n; i++) {
      char ch = gene.charAt(i);
      int val = map.get(ch);
      if (val + 1 > m) {
        start = i;
        break;
      }
      map.put(ch, val + 1);
    }
    if (start == n) return 0;
    for (int i = n - 1; i > start; i--) {
      char ch = gene.charAt(i);
      int val = map.get(ch);
      if (val + 1 > m) {
        return i - start + 1;
      }
      map.put(ch, val + 1);
    }
    return 1;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    try (BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {

      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      String gene = scanner.nextLine();

      int result = steadyGene(gene);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      scanner.close();
    }
  }
}
