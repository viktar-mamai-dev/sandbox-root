package com.mamay.leetcode.contest.weeks_80_90.week84;

public class ImageOverlap {

	public int largestOverlap(int[][] A, int[][] B) {
		int max = 0;
		int n = A.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (A[i][j] == 0)
					continue;
				int f = find(A, i, j, B);
				max = Math.max(max, f);
			}
		}
		return max;
	}

	private static int find(int A[][], int ai, int aj, int B[][], int bi, int bj) {
		int n = A.length;
		int leni = Math.min(n - ai, n - bi);
		int lenj = Math.min(n - aj, n - bj);
		int res = 0;
		for (int i = 0; i < leni; i++) {
			for (int j = 0; j < lenj; j++) {
				if (A[ai + i][aj + j] == B[bi + i][bj + j] && 1 == A[ai + i][aj + j]) {
					res++;
				}
			}
		}
		return res;
	}

	private static int find(int A[][], int ai, int aj, int B[][]) {
		int max = 0;
		int n = B.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (B[i][j] == 0)
					continue;
				int f = find(A, ai, aj, B, i, j);
				max = Math.max(max, f);
			}
		}
		return max;
	}
}
/**
[[1,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1,1,0,1,0,0,0],[0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,0,1,1,0,0,0,0,0,1,0],[0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,1,0,1,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,1],[0,0,0,1,0,0,1,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,1,0,1,1,1,0,0,0,0,0,1],[1,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,0,1,1],[0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0],[0,0,0,0,1,0,0,0,0,1,1,0,0,0,1,0,1,1,0,1,0,1,0,0,0,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0],[0,0,1,0,0,0,0,0,0,1,1,0,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,0,0,0],[0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0],[0,1,1,0,1,0,1,0,1,0,0,0,0,1,0,1,0,0,1,0,1,1,1,0,0,0,0,0,1,1],[1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1],[0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0],[1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,1,0,1,0,0,0],[0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0],[0,0,1,0,0,0,0,1,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0],[1,0,1,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,0,0,0,0],[1,0,1,1,1,0,1,1,1,0,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0],[1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,1,0],[0,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,0,1,1,0,0,1,0,1,0,0,0,0,1,0],[0,1,1,1,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,0,1,0,0,1,0,0,1,1,1],[0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,1,1,1,0,0,1,0],[0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,1,0,1,0,0,1,0,0,0,0,0,0],[0,0,1,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1],[0,0,0,1,0,0,1,0,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1]]
[[1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1,0,1,0,1,1,1,1],[1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,0,0],[1,1,1,0,1,1,1,1,1,0,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,0,0,1,1],[1,1,1,1,1,1,0,0,1,0,0,1,1,1,0,1,0,1,1,1,1,0,1,0,1,1,0,0,1,1],[1,1,1,0,1,0,1,0,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,1,0,1,1],[0,1,1,1,1,0,1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,1,1,1,1,1],[0,1,1,1,1,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,0],[1,1,1,1,0,0,1,1,0,1,1,1,0,0,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0],[1,1,1,1,1,1,0,0,0,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0],[1,1,1,0,1,1,0,0,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,0],[0,1,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1],[1,0,1,0,1,0,0,0,0,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0],[1,0,1,1,0,0,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,1,1],[0,1,1,0,1,1,1,0,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1],[1,0,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,0,0,1,1,0,1,1,1,0,1,1,1],[1,1,1,1,0,0,1,1,1,0,1,1,0,1,1,0,1,1,0,1,1,1,0,1,1,1,1,1,1,1],[0,0,0,1,0,1,0,1,1,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0],[1,1,1,1,0,0,1,1,1,0,1,1,0,0,1,1,0,1,1,1,1,0,1,0,0,1,1,1,1,0],[0,1,1,1,0,1,0,1,1,0,1,0,1,1,1,1,0,1,1,1,0,1,0,0,1,0,1,1,1,1],[1,1,1,1,1,0,0,1,0,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1],[1,0,0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,0],[1,1,1,1,1,0,0,1,1,0,0,0,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,1,1,1],[1,1,1,1,0,1,0,0,1,0,0,1,1,1,0,1,0,1,1,1,1,0,0,1,1,1,1,1,1,1],[1,1,0,0,0,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,1,1],[0,0,1,1,0,1,1,0,0,1,1,0,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,0],[1,0,1,1,0,0,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,0,1,0],[0,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,0,1,0],[0,1,1,0,1,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,0,0,0,1],[1,1,0,1,0,1,1,1,0,1,1,0,0,1,0,0,1,1,0,0,1,0,0,1,1,0,0,0,0,1],[0,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,0,1]]
*/