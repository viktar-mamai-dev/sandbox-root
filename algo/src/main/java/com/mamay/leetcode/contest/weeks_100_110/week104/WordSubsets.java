package com.mamay.leetcode.contest.weeks_100_110.week104;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WordSubsets {

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static List<String> wordSubsets(String[] A, String[] B) {
    List<HashMap<Character, Integer>> aMaps = new ArrayList<>();
    List<HashMap<Character, Integer>> bMaps = new ArrayList<>();

    for (String str : A) {
      aMaps.add(generateMapFromString(str));
    }

    for (String str : B) {
      bMaps.add(generateMapFromString(str));
    }

    HashMap<Character, Integer> commonBMap = generateCommonMap(bMaps);

    ArrayList<String> list = new ArrayList<>();
    int n = A.length;
    for (int i = 0; i < n; i++) {
      HashMap<Character, Integer> aMap = aMaps.get(i);
      if (contains(aMap, commonBMap)) {
        list.add(A[i]);
      }
    }
    return list;
  }

  private static HashMap<Character, Integer> generateCommonMap(
      List<HashMap<Character, Integer>> bMaps) {
    HashMap<Character, Integer> commonMap = new HashMap<>();
    for (HashMap<Character, Integer> bMap : bMaps) {
      for (Map.Entry<Character, Integer> entry : bMap.entrySet()) {
        Character key = entry.getKey();
        Integer value = entry.getValue();
        Integer value2 = commonMap.get(key);
        if (value2 == null || value2 < value) {
          commonMap.put(key, value);
        }
      }
    }
    return commonMap;
  }

  private static boolean contains(
      HashMap<Character, Integer> aMap, HashMap<Character, Integer> bMap) {
    for (Map.Entry<Character, Integer> entry : bMap.entrySet()) {
      Character key = entry.getKey();
      Integer value = entry.getValue();
      Integer value2 = aMap.get(key);
      if (value2 == null || value2 < value) {
        return false;
      }
    }
    return true;
  }

  private static HashMap<Character, Integer> generateMapFromString(String str) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (char ch : str.toCharArray()) {
      Integer value = map.get(ch);
      if (value == null) {
        value = 0;
      }
      map.put(ch, value + 1);
    }
    return map;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {

        String a[] = readStringArray(scanner);
        String b[] = readStringArray(scanner);

        writer.println(wordSubsets(a, b));
      }
      scanner.close();
    }
  }

  private static String[] readStringArray(Scanner scanner) {
    String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
    int len = tokens.length;
    System.out.println("Length of array is: " + len);
    return tokens;
  }
}
