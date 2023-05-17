package com.mamay.hackerrank.practice.constructive;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FlippingMatrix {

  // Complete the flippingMatrix function below.
  static int flippingMatrix(int[][] matrix) {
    return 0;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int qItr = 0; qItr < q; qItr++) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[][] matrix = new int[2 * n][2 * n];

      for (int i = 0; i < 2 * n; i++) {
        String[] matrixRowItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int j = 0; j < 2 * n; j++) {
          int matrixItem = Integer.parseInt(matrixRowItems[j]);
          matrix[i][j] = matrixItem;
        }
      }

      int result = flippingMatrix(matrix);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
