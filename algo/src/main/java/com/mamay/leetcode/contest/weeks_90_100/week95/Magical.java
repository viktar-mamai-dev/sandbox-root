package com.mamay.leetcode.contest.weeks_90_100.week95;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Magical {

	private static final int MOD = 1000000007;

	public static int nthMagicalNumber(int N, int A, int B) {

		if (A > B) {
			int temp = A;
			A = B;
			B = temp;
		}

		int gcd = gcd(A, B);
		long lcm = ((long) A * B) / gcd;

		int divisorsInBunch = (int) (lcm / A + lcm / B - 1);
		int q = N / divisorsInBunch;

		long res = lcm * q;
		int r = N % divisorsInBunch;

		if (r != 0) {
			int a = 0, b = 0;
			for (int i = 1; i <= r; i++) {
				if (a + A <= b + B) {
					a += A;
				} else {
					b += B;
				}
			}
			res += Math.max(a, b);
		}
		return (int) (res % MOD);
	}

	// a <= b always here
	private static int gcd(int a, int b) {
		while (a != 0) {
			int temp = b % a;
			b = a;
			a = temp;
		}
		return b;
	}

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File(INPUT_FILE));
		int q = scanner.nextInt();
		scanner.nextLine();

		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
			for (int qi = 0; qi < q; qi++) {

				int n = scanner.nextInt();
				int a = scanner.nextInt();
				int b = scanner.nextInt();

				writer.println(nthMagicalNumber(n, a, b));
			}
			scanner.close();
		}
	}
}
