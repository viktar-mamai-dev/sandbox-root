package com.mamay.leetcode.contest.weeks_90_100.week90;

import java.util.Scanner;

public class Mirror {

	public static int mirrorReflection(int p, int q) {
		if (q == 0)
			return 0;
		if (q == p)
			return 1;
		int g = gcd(p, q);
		int k = q / g;
		int l = p / g;
		if (k % 2 == 1 && l % 2 == 1) {
			return 1;
		}
		if (k % 2 == 0 && l % 2 == 1) {
			return 0;
		}
		if (k % 2 == 1 && l % 2 == 0) {
			return 2;
		}
		return -1;
	}
	
	private static int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int p = scanner.nextInt();
		int q = scanner.nextInt();
		System.out.println(mirrorReflection(p, q));
		scanner.close();
	}
}
