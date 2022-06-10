package com.mamay.leetcode.contest.weeks_80_90.week85;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Dominoes {

	public static String pushDominoes(String dominoes) {
		char arr[] = dominoes.toCharArray();
		int len = arr.length;
		char res[] = new char[len];
		Arrays.fill(res, '.');
		int prevIdx = -1, nextIdx = 0;
		char prev = '.', next;
		int i = 0;
		while (i < len) {
			while (i < len && arr[i] == '.') {
				i++;
			}
			if(i >= len) {
				break;
			}
			nextIdx = i;
			next = arr[i];
			res[i] = next;
			if (next == 'L') {
				if (prev == 'R') {
					int l = (nextIdx - prevIdx - 1) / 2;
					for (int k = prevIdx + 1; k <= prevIdx + l; k++) {
						res[k] = 'R';
					}
					for (int k = nextIdx - l; k <= nextIdx - 1; k++) {
						res[k] = 'L';
					}
				} else {
					for (int k = prevIdx + 1; k < nextIdx; k++) {
						res[k] = 'L';
					}
				}
			} else if (prev == 'R') {
				for (int k = prevIdx + 1; k < nextIdx; k++) {
					res[k] = 'R';
				}
			}
			i++;
			prevIdx = nextIdx;
			prev = next;
		}
		if(i<len) {
			if(prev == 'R' ) {
				for (int k = prevIdx + 1; k < nextIdx; k++) {
					res[k] = 'R';
				}
			}
		}
		return new String(res);
	}

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File(INPUT_FILE));
		int q = scanner.nextInt();
		scanner.nextLine();

		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
			for (int qi = 0; qi < q; qi++) {
				String s = scanner.nextLine();

				writer.println(pushDominoes(s));
			}
			scanner.close();
		}
	}
}
