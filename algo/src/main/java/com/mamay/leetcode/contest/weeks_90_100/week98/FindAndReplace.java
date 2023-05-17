package com.mamay.leetcode.contest.weeks_90_100.week98;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAndReplace {

  private static final int ALPHABET_SIZE = 26;

  public List<String> findAndReplacePattern(String[] words, String pattern) {
    List<String> resultList = new ArrayList<>();
    char[] patternArray = pattern.toCharArray();

    for (String word : words) {
      if (isWordMatchesPattern(word, patternArray)) {
        resultList.add(word);
      }
    }
    return resultList;
  }

  private static boolean isWordMatchesPattern(String word, char[] patternArray) {
    int len = word.length();
    char[] letters = word.toCharArray();
    char[] arr = new char[ALPHABET_SIZE];
    Arrays.fill(arr, '#');

    for (int i = 0; i < len; i++) {
      int index = patternArray[i] - 'a';
      char letter = letters[i];

      if (arr[index] == '#') {
        arr[index] = letter;
      } else {
        if (arr[index] != letter) {
          return false;
        }
      }
    }
    for (int i1 = 0; i1 < ALPHABET_SIZE - 1; i1++) {
      for (int i2 = i1 + 1; i2 < ALPHABET_SIZE; i2++) {
        if (arr[i1] != '#' && arr[i2] != '#' && arr[i1] == arr[i2]) {
          return false;
        }
      }
    }
    return true;
  }
}
