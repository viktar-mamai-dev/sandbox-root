package com.mamay.hackerrank.practice.dp;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class BearAndSteady {

  public static int steadyGene(String gene) {
    // Write your code here
    int n = gene.length();
    int arr[] = new int[4];
    Arrays.fill(arr, -n / 4);
    for (char ch : gene.toCharArray()) {
      int i = getIdx(ch);
      arr[i]++;
    }

    if (isComplete(arr)) return 0;

    int subseq[] = new int[4];
    Arrays.fill(subseq, 0);
    int min = n;
    int start = 0;
    for (int i = 0; i < n; i++) {
      char ch = gene.charAt(i);
      int idx = getIdx(ch);
      subseq[idx]++;
      while (start < n && covers(subseq, arr)) {
        min = Math.min(min, i - start + 1);
        // System.out.println("Covers at " + start + " : " + i);
        int startIdx = getIdx(gene.charAt(start++));
        subseq[startIdx]--;
      }
    }
    return min;
  }

  private static boolean covers(int[] subseq, int[] arr) {
    for (int i = 0; i < subseq.length; i++) {
      if (subseq[i] < arr[i]) return false;
    }
    return true;
  }

  private static boolean isComplete(int arr[]) {
    for (int elem : arr) {
      if (elem != 0) return false;
    }
    return true;
  }

  private static int getIdx(char ch) {
    switch (ch) {
      case 'A':
        return 0;
      case 'C':
        return 1;
      case 'G':
        return 2;
      case 'T':
        return 3;
    }
    return -1;
  }

  private static Path inputPath = Paths.get("hackerrank", "src", "main", "resources", "input.txt");
  private static Path outputPath =
      Paths.get("hackerrank", "src", "main", "resources", "output.txt");

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(new FileInputStream(inputPath.toFile())));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath.toFile()));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    String gene = bufferedReader.readLine();

    BearAndSteady main = new BearAndSteady();
    int result = main.steadyGene(gene);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
