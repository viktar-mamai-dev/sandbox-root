package com.mamay.leetcode.contest.weeks_80_90.week87;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Backspace {

  public static boolean backspaceCompare(String S, String T) {
    String Sres = convertString(S);
    String Tres = convertString(T);
    return Sres.equals(Tres);
  }

  private static String convertString(String T) {
    int idx;
    int lenT = T.length();
    char chT[] = T.toCharArray();
    char resT[] = new char[lenT];
    idx = 0;
    for (char ch : chT) {
      if (ch == '#') {
        idx = idx > 0 ? idx - 1 : idx;
      } else {
        resT[idx++] = ch;
      }
    }
    String Tres = new String(resT, 0, idx);
    return Tres;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        // TODO
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        writer.println(backspaceCompare(str1, str2));
      }
      scanner.close();
    }
  }
}
