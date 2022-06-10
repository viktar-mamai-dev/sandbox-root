package com.mamay.leetcode.contest.weeks_100_110.week100;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Bitwise {

	private Set<Integer> bitSet;

	public int subarrayBitwiseORs(int[] a) {
		int size = a.length;
		bitSet = new HashSet<Integer>();
		for (int i = 0; i < size; i++) {
			int val = 0;
			for (int j = i; j < size; j++) {
				val = val | a[j];
				if (!bitSet.contains(val)) {
					bitSet.add(val);
				}
			}
		}

		System.out.println(bitSet);
		return bitSet.size();
	}

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File(INPUT_FILE));
		int q = scanner.nextInt();
		scanner.nextLine();

		Bitwise main = new Bitwise();
		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
			for (int qi = 0; qi < q; qi++) {
				int A[] = readArray(scanner);

				writer.println(main.subarrayBitwiseORs(A));
			}
			scanner.close();
		}
	}

	private static int[] readArray(Scanner scanner) {
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
