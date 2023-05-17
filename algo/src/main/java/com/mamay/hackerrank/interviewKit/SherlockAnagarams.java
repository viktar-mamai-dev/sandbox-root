package com.mamay.hackerrank.interviewKit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SherlockAnagarams {
  private static final Scanner scanner = new Scanner(System.in);

  // Complete the sherlockAndAnagrams function below.
  static int sherlockAndAnagrams(String s) {
    // TODO
    return 0;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {

      int q = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int qItr = 0; qItr < q; qItr++) {
        String s = scanner.nextLine();

        int result = sherlockAndAnagrams(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
      }
    }
    scanner.close();
  }
}
