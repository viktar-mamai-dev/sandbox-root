package com.mamay.leetcode.practice.easy2;

public class CountAndSay_38 {
  public String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }
    String prevStr = "1";

    StringBuilder builder;
    for (int i = 2; i <= n; i++) {
      int count = 1;
      char prevChar = prevStr.charAt(0);
      builder = new StringBuilder();
      for (int k = 1; k < prevStr.length(); k++) {
        char ch = prevStr.charAt(k);
        if (ch == prevChar) {
          count++;
        } else {
          builder.append(count).append(prevChar);
          prevChar = ch;
          count = 1;
        }
      }
      builder.append(count).append(prevChar);
      prevStr = builder.toString();
    }
    return prevStr;
  }
}
