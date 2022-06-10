package com.mamay.leetcode.contest.weeks_80_90.week89;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SimilarStrings {

	private static int kSimilarity(String A, String B) {
		List<Character> alist = new LinkedList<Character>();
		List<Character> blist = new LinkedList<Character>();

		int len = A.length();
		for (int i = 0; i < len; i++) {
			char cha = A.charAt(i);
			char chb = B.charAt(i);
			if (cha != chb) {
				alist.add(cha);
				blist.add(chb);
			}
		}

		if (alist.isEmpty())
			return 0;
		int res = 0;

		while (!alist.isEmpty()) {
			boolean flag = false;
			int idx = -1;
			for (int i = 0; i < blist.size() && !flag; i++) {
				if (alist.get(0) == blist.get(i)) {
					if (alist.get(i) == blist.get(0)) {
						flag = true;
					}
					idx = i;
				}
			}

			if (flag) {
				alist.remove(idx);
				blist.remove(idx);
			} else {
				blist.set(idx, blist.get(0));
			}
			alist.remove(0);
			blist.remove(0);
			res++;
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
				// TODO
				// int array[] = readArray(scanner);

				String a = scanner.nextLine().replaceAll("\"", "");
				String b = scanner.nextLine().replaceAll("\"", "");

				writer.println(kSimilarity(a, b));
			}
			scanner.close();
		}
	}
}
