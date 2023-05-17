package com.mamay.leetcode.contest.weeks_80_90.week85;

import java.util.ArrayList;
import java.util.List;

public class UnCommonWords {

  public String[] uncommonFromSentences(String A, String B) {
    List<String> resultList = new ArrayList<String>();
    String[] arr1 = A.split(" ");
    String[] arr2 = B.split(" ");
    for (String s : arr1) {
      int count = numOfOccurences(s, arr1, arr2);
      if (count == 1) {
        resultList.add(s);
      }
    }

    for (String s : arr2) {
      int count = numOfOccurences(s, arr1, arr2);
      if (count == 1) {
        resultList.add(s);
      }
    }

    int sz = resultList.size();
    return resultList.toArray(new String[sz]);
  }

  private int numOfOccurences(String s, String[] arr1, String[] arr2) {
    int count = 0;
    for (String str : arr1) {
      if (s.equalsIgnoreCase(str)) count++;
    }
    for (String str : arr2) {
      if (s.equalsIgnoreCase(str)) count++;
    }
    return count;
  }
}
