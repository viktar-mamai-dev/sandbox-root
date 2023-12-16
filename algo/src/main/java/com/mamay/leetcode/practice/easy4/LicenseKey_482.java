package com.mamay.leetcode.practice.easy4;

public class LicenseKey_482 {
  public String licenseKeyFormatting(String S, int K) {
    String s1 = S.replaceAll("-", "");
    s1 = s1.toUpperCase();
    int len = s1.length();
    int idx = len % K;
    StringBuilder builder = new StringBuilder();
    if (idx != 0) {
      builder.append(s1.substring(0, idx));
      if (idx < len) {
        builder.append("-");
      }
    }
    while (idx < len) {
      builder.append(s1.substring(idx, idx + K));
      idx += K;
      if (idx < len) {
        builder.append("-");
      }
    }
    return builder.toString();
  }
}
