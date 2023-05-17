package com.mamay.hackerrank.contest.codeagon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TShaped {

  // Complete the solve function below.
  static String solve(List<List<Integer>> grid) {
    Map<Integer, List<Integer>> xMap = new HashMap<>();
    Map<Integer, List<Integer>> yMap = new HashMap<>();

    for (List<Integer> point : grid) {
      Integer x = point.get(0);
      Integer y = point.get(1);

      List<Integer> valueList = xMap.get(x);
      if (valueList == null) {
        valueList = new ArrayList<>();
      }
      valueList.add(y);
      xMap.put(x, valueList);

      valueList = yMap.get(y);
      if (valueList == null) {
        valueList = new ArrayList<>();
      }
      valueList.add(x);
      yMap.put(y, valueList);
    }
    if (checkMaps(xMap, yMap)) {
      return "YES";
    }

    return "NO";
  }

  static boolean checkMaps(Map<Integer, List<Integer>> xMap, Map<Integer, List<Integer>> yMap) {
    Integer c1 = checkMap(xMap);
    if (c1 == null) {
      return false;
    }
    Integer c2 = checkMap(yMap);
    if (c2 == null) {
      return false;
    }

    if (c1 * c2 != 0 || c1 + c2 == 0) {
      return false;
    }
    return true;
  }

  static Integer checkMap(Map<Integer, List<Integer>> map) {
    ArrayList<Integer> keySet = new ArrayList<Integer>(map.keySet());
    if (keySet.size() != 3) {
      return null;
    }
    Collections.sort(keySet);
    Integer x1 = keySet.get(0);
    Integer x2 = keySet.get(1);
    Integer x3 = keySet.get(2);
    if (x1 != x2 - 1 || x2 != x3 - 1) {
      return null;
    }

    List<Integer> value1 = map.get(x1);
    List<Integer> value2 = map.get(x2);
    List<Integer> value3 = map.get(x3);

    if (value1.size() * value2.size() * value3.size() != 3) {
      return null;
    }
    for (Integer x : keySet) {
      if (map.get(x).size() == 3) {
        return x - x2;
      }
    }
    return null;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, t)
        .forEach(
            tItr -> {
              try {
                List<List<Integer>> points = new ArrayList<>();

                IntStream.range(0, 5)
                    .forEach(
                        i -> {
                          try {
                            points.add(
                                Stream.of(
                                        bufferedReader
                                            .readLine()
                                            .replaceAll("\\s+$", "")
                                            .split(" "))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList()));
                          } catch (IOException ex) {
                            throw new RuntimeException(ex);
                          }
                        });

                String result = solve(points);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
