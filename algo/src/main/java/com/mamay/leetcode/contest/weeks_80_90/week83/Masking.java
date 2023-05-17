package com.mamay.leetcode.contest.weeks_80_90.week83;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Masking {

  public String maskPII(String S) {

    S = S.toLowerCase();
    Pattern emailPattern = Pattern.compile("(([a-z])+)@([a-z])+\\.([a-z])+");
    String reg = "\\+|\\-|\\(|\\)|\\ ";
    Pattern phonePattern = Pattern.compile("([0-9]|" + reg + ")+");

    Matcher m = emailPattern.matcher(S);
    String res = null;
    if (m.matches()) {
      System.out.println("Email");
      res = maskEmail(S, m.group(1));
    }

    m = phonePattern.matcher(S);
    if (m.matches()) {
      System.out.println("Phone");
      res = maskPhone(S, reg);
    }
    return res;
  }

  private String maskEmail(String s, String group0) {
    int len = group0.length();
    String newStr = group0.charAt(0) + "*****" + group0.charAt(len - 1);
    String res = s.replaceFirst(group0, newStr);
    return res;
  }

  private String maskPhone(String s, String reg) {
    String phone = s.replaceAll(reg, "");
    int prefixSize = phone.length() - 10;
    String prefix = "";
    if (prefixSize > 0) {
      prefix = "+";
      for (int i = 0; i < prefixSize; i++) {
        prefix += "*";
      }
      prefix += "-";
    }
    String postFix = phone.substring(phone.length() - 4);
    String postFixFull = "***-***-" + postFix;
    return prefix + postFixFull;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    Masking main = new Masking();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        String s = scanner.nextLine();

        writer.println(main.maskPII(s));
      }
      scanner.close();
    }
  }
}
