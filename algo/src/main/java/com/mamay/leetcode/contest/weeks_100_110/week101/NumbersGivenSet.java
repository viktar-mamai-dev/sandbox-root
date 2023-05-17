package com.mamay.leetcode.contest.weeks_100_110.week101;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class NumbersGivenSet {

  private int a;
  private int b;

  public int atMostNGivenDigitSet(String[] D, int n) {
    a = D.length;
    int d[] = new int[a];
    for (int i = 0; i < a; i++) {
      d[i] = Integer.parseInt(D[i]);
    }
    int numbers[] = getNumbers(n);
    b = numbers.length;
    long result = initial(a, b);
    result += make(0, d, numbers);
    return (int) result;
  }

  private long initial(int a, int b) {
    if (a == 1) {
      return b - 1;
    }
    return a * ((long) Math.pow(a, b - 1) - 1) / (a - 1);
  }

  private long make(int nIndex, int d[], int numbers[]) {
    int ai = findLower(numbers[nIndex], d);
    long result = ai * (long) Math.pow(a, b - nIndex - 1);
    if (ai < a) {
      if (d[ai] == numbers[nIndex]) {
        if (nIndex < numbers.length - 1) {
          result += make(nIndex + 1, d, numbers);
        } else result += 1;
      }
    }
    return result;
  }

  private int findLower(int value, int[] d) {
    int i = 0;
    int count = 0;
    while (i < a && d[i] < value) {
      count++;
      i++;
    }
    return count;
  }

  private int[] getNumbers(int N) {
    int count = 0;
    int Ncopy = N;
    while (N != 0) {
      count++;
      N /= 10;
    }
    N = Ncopy;
    int numbers[] = new int[count];
    int i = count - 1;
    while (N != 0) {
      numbers[i] = N % 10;
      N /= 10;
      i--;
    }
    return numbers;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    NumbersGivenSet main = new NumbersGivenSet();
    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        // TODO
        // int array[] = readArray(scanner);

        String arr[] = readStringArray(scanner);
        int N = scanner.nextInt();
        scanner.nextLine();

        writer.println(main.atMostNGivenDigitSet(arr, N));
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
