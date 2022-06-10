package com.mamay.leetcode.contest.weeks_80_90.week83;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Consecutive {

	public int consecutiveNumbersSum(int N) {
		int count = 0;

		return count;
	}

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File(INPUT_FILE));
		int q = scanner.nextInt();
		scanner.nextLine();

		Consecutive main = new Consecutive();
		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
			for (int qi = 0; qi < q; qi++) {
				int n = scanner.nextInt();

				writer.println(main.consecutiveNumbersSum(n));
			}
			scanner.close();
		}
	}
}
