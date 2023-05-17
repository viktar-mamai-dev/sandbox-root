package com.mamay.leetcode.practice.easy2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Decrypt_1309 {
  public String freqAlphabets(String s) {
    int n = s.length();
    int i = 0;
    int idx = s.indexOf('#');
    StringBuilder builder = new StringBuilder();
    while (idx != -1) {
      int k = idx - 2;
      while (i < k) {
        char ch = (char) ('a' + (s.charAt(i) - '0') - 1);
        builder.append(ch);
        i++;
      }
      char ch = (char) (Integer.parseInt(s.substring(k, k + 2)) + 'a' - 1);
      builder.append(ch);
      i = idx + 1;
      idx = s.indexOf('#', idx + 1);
    }

    while (i < n) {
      char ch = (char) ('a' + (s.charAt(i) - '0') - 1);
      builder.append(ch);
      i++;
    }
    return builder.toString();
  }

  public static void main(String[] args) throws IOException {
    Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
    Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
    Decrypt_1309 main = new Decrypt_1309();
    try (Scanner scanner = new Scanner(inputPath)) {
      int q = scanner.nextInt();
      scanner.nextLine();

      try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
        for (int qi = 0; qi < q; qi++) {
          String date1 = scanner.nextLine();
          writer.println(main.freqAlphabets(date1));
        }
      }
    }
  }
}
