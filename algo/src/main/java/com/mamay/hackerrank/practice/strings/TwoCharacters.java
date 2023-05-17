package com.mamay.hackerrank.practice.strings;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TwoCharacters {
  // Complete the alternate function below.
  static int alternate(String s) {
    HashMap<Character, ArrayList<Integer>> map = new HashMap<>();

    int len = s.length();
    for (int i = 0; i < len; i++) {
      char ch = s.charAt(i);
      ArrayList<Integer> list = map.get(ch);
      if (list == null) {
        list = new ArrayList<>();
      }
      list.add(i);
      map.put(ch, list);
    }

    ArrayList<ArrayList<Integer>> values = new ArrayList<ArrayList<Integer>>(map.values());
    int mapLen = values.size();
    int maxWord = 0;
    for (int i = 0; i < mapLen - 1; i++) {
      ArrayList<Integer> iList = values.get(i);
      for (int j = i + 1; j < mapLen; j++) {
        ArrayList<Integer> jList = values.get(j);
        if (validate(iList, jList)) {
          int tmp = iList.size() + jList.size();
          if (tmp > maxWord) {
            maxWord = tmp;
            System.out.println(iList.toString() + jList.toString() + maxWord);
          }
        }
      }
    }
    return maxWord;
  }

  private static boolean validate(ArrayList<Integer> iList, ArrayList<Integer> jList) {
    boolean isFirst;
    int i = 0, j = 0;
    if (iList.get(0) < jList.get(0)) {
      isFirst = true;
      i++;
    } else {
      isFirst = false;
      j++;
    }
    while (i < iList.size() && j < jList.size()) {
      if (iList.get(i) < jList.get(j)) {
        if (isFirst) return false;
        isFirst = true;
        i++;
      } else {
        if (!isFirst) return false;
        isFirst = false;
        j++;
      }
    }
    if (i < iList.size()) {
      if (isFirst || i < iList.size() - 1) return false;
    }

    if (j < jList.size()) {
      if (!isFirst || j < jList.size() - 1) return false;
    }
    return true;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

    int l = Integer.parseInt(bufferedReader.readLine().trim());

    String s = bufferedReader.readLine();

    int result = alternate(s);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
