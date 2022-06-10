package com.mamay.leetcode.contest.weeks_90_100.week97;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Bipartition {

	public static boolean possibleBipartition(int N, int[][] dislikes) {
		short setNumber[] = new short[N + 1];
		Arrays.fill(setNumber, (short) 0);

		List<int[]> list = new ArrayList<>(Arrays.asList(dislikes));
		while (!list.isEmpty()) {
			int d0 = list.get(0)[0];
			int d1 = list.get(0)[1];
			setNumber[d0] = 1;
			setNumber[d1] = -1;

			boolean flag = checkList(list, setNumber);
			if (!flag) {
				return false;
			}

		}
		return true;
	}

	private static boolean checkList(List<int[]> initialList, short[] setNumber) {
		boolean wasChanged = true;
		while (wasChanged) {
			wasChanged = false;

			Iterator<int[]> iterator = initialList.iterator();
			while (iterator.hasNext()) {
				int dislike[] = iterator.next();
				int d0 = dislike[0];
				int d1 = dislike[1];
				short val1 = setNumber[d0];
				short val2 = setNumber[d1];

				if (val1 != 0 && val2 != 0) {
					iterator.remove();
					if (val1 == val2)
						return false;
				} else if (val1 != 0) {
					iterator.remove();
					setNumber[d1] = (short) -val1;
					wasChanged = true;
				} else if (val2 != 0) {
					iterator.remove();
					setNumber[d0] = (short) -val2;
					wasChanged = true;
				}
			}
		}
		return true;
	}

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File(INPUT_FILE));
		int q = scanner.nextInt();
		scanner.nextLine();

		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
			for (int qi = 0; qi < q; qi++) {
				// TODO
				int n = scanner.nextInt();
				scanner.nextLine();

				int matrix[][] = readMatrixOneLined(scanner);

				writer.println(possibleBipartition(n, matrix));
			}
			scanner.close();
		}
	}

	private static int[][] readMatrixOneLined(Scanner scanner) {
		String lines[] = scanner.nextLine().split("(],)");
		int len = lines.length;
		int res[][] = new int[len][];

		for (int k = 0; k < len; k++) {
			String tokens[] = lines[k].replaceAll("]|\\[", "").split(",");
			int len2 = tokens.length;
			res[k] = new int[len2];
			for (int m = 0; m < len2; m++) {
				res[k][m] = Integer.parseInt(tokens[m]);
			}
		}
		System.out.println("Size of matrix is: " + len + " * " + res[0].length);
		return res;
	}
}
