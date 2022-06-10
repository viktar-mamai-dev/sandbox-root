package com.mamay.leetcode.contest.weeks_80_90.week84;

public class FlippingImage {

	public int[][] flipAndInvertImage(int[][] A) {
		int n = A.length;
		int res[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res[i][n - j] = 1 - res[i][j];
			}
		}
		return res;
	}
}
