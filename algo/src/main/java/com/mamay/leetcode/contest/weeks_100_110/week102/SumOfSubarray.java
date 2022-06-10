package com.mamay.leetcode.contest.weeks_100_110.week102;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumOfSubarray {

	// [3,1,2,4]
	public int sumSubarrayMins(int[] A) {
		int ans = 0;
		List<Integer> cur = new ArrayList<>();
		for (int x : A) {
			List<Integer> cur2 = new ArrayList<>();
			for (int y : cur) {
				cur2.add(Math.min(x, y));

			}
			cur2.add(x);
			cur = cur2;
			ans += cur2.stream().reduce(0, (v1, v2) -> v1 + v2);
		}
		return ans;
	}

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File(INPUT_FILE));
		int q = scanner.nextInt();
		scanner.nextLine();

		SumOfSubarray m = new SumOfSubarray();

		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
			for (int qi = 0; qi < q; qi++) {
				int array[] = readIntegerArray(scanner);
				writer.println(m.sumSubarrayMins(array));
			}
			scanner.close();
		}
	}

	private static int[] readIntegerArray(Scanner scanner) {
		String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
		int len = tokens.length;
		int res[] = new int[len];
		for (int k = 0; k < len; k++) {
			res[k] = Integer.parseInt(tokens[k]);
		}
		System.out.println("Length of array is: " + len);
		return res;
	}
}
