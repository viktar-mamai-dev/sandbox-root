package com.mamay.hackerrank.practice.constructive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GamingArray {

	// Complete the gamingArray function below.
	static String gamingArray(int[] arr) {
		int currentMax = 0;
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > currentMax) {
				currentMax = arr[i];
				count++;
			}
		}

		return count % 2 == 1 ? "BOB" : "ANDY";
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

		int g = scanner.nextInt();
		// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int gItr = 0; gItr < g; gItr++) {
			int arrCount = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] arr = new int[arrCount];

			String[] arrItems = scanner.nextLine().split(" ");
			// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < arrCount; i++) {
				int arrItem = Integer.parseInt(arrItems[i]);
				arr[i] = arrItem;
			}

			String result = gamingArray(arr);

			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
