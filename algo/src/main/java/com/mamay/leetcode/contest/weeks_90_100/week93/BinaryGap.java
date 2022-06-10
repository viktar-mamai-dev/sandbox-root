package com.mamay.leetcode.contest.weeks_90_100.week93;

import java.util.Scanner;

public class BinaryGap {

	public static int binaryGap(int N) {
		char arr[] = Integer.toBinaryString(N).toCharArray();
		int max = 0, lastOne = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == '1') {
				max = Math.max(max, i - lastOne);
				lastOne = i;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		System.out.println(binaryGap(N));
		scanner.close();

	}

}
