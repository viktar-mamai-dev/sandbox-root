package com.mamay.leetcode.practice.easy3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Subdomain_811 {
  public static List<String> subdomainVisits(String[] cpdomains) {
    HashMap<String, Long> map = new HashMap<>();

    for (String str : cpdomains) {
      int idxSpace = str.indexOf(' ');
      Integer visitedCount = Integer.parseInt(str.substring(0, idxSpace));
      String fullDomain = str.substring(idxSpace + 1);
      map.putIfAbsent(fullDomain, 0L);
      map.computeIfPresent(fullDomain, (key, value) -> value + visitedCount);
      int idxDot = -1;
      while ((idxDot = fullDomain.indexOf('.', idxDot + 1)) != -1) {
        String subDomain = fullDomain.substring(idxDot + 1);
        map.putIfAbsent(subDomain, 0L);
        map.computeIfPresent(subDomain, (key, value) -> value + visitedCount);
      }
    }

    List<String> list = new LinkedList<>();
    for (Map.Entry<String, Long> entry : map.entrySet()) {
      list.add(entry.getValue() + " " + entry.getKey());
    }
    return list;
  }

  public static void main(String[] args) throws IOException {
    Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
    Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
    try (Scanner scanner = new Scanner(inputPath)) {
      int q = scanner.nextInt();
      scanner.nextLine();

      try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
        String[] list = new String[q];
        for (int qi = 0; qi < q; qi++) {
          list[qi] = scanner.nextLine();
        }
        writer.println(subdomainVisits(list));
      }
    }
  }
}
