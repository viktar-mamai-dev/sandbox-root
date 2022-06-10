package com.mamay.leetcode.contest.weeks_90_100.week97;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SpiralMatrix {

	public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
		int n = R * C;
		int res[][] = new int[n][2];
		int direction = 0;
		int len = 2;
		res[0][0] = r0;
		res[0][1] = c0;
		int usedLen = 1;
		int nextIdx = 1;
		int usedCells = 1;
		int currentX = r0, currentY = c0;
		boolean toChangeLen = false;
		while (usedCells < n) {

			if (direction == 0) {
				currentY++;
			} else if (direction == 1) {
				currentX++;
			} else if (direction == 2) {
				currentY--;
			} else if (direction == 3) {
				currentX--;
			}
			if (0 <= currentX && currentX < R && 0 <= currentY && currentY < C) {
				res[nextIdx][0] = currentX;
				res[nextIdx][1] = currentY;
				usedCells++;
				nextIdx++;
			}
			usedLen++;
			if (usedLen >= len) {
				usedLen = 1;
				direction = (direction + 1) % 4;
				if (toChangeLen) {
					len++;
				}
				toChangeLen = !toChangeLen;
			}

		}

		return res;

	}

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File(INPUT_FILE));
		int q = scanner.nextInt();
		scanner.nextLine();

		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
			for (int qi = 0; qi < q; qi++) {
				int r = scanner.nextInt();
				int c = scanner.nextInt();
				int r0 = scanner.nextInt();
				int c0 = scanner.nextInt();
				scanner.nextLine();
				writer.println(Arrays.deepToString(spiralMatrixIII(r, c, r0, c0)));

			}
			scanner.close();
		}
	}
}
