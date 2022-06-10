package com.mamay.leetcode.contest.weeks_90_100.week93;

import java.util.Scanner;

public class Minimum {

	public static int minRefuelStops(int target, int startFuel, int[][] stations) {
		int n = stations.length;
		boolean used[] = new boolean[n];
		int fuel = startFuel;
		int usedCount = 0;
		int idx = 0;
		while (fuel < target && usedCount < n) {
			while (idx < n && fuel >= stations[idx][0]) {
				idx++;
			}
			int maxIdx = findMax(stations, idx, used);
			if (maxIdx == -1)
				return -1;
			used[maxIdx] = true;
			usedCount++;
			fuel += stations[maxIdx][1];
		}

		if (fuel < target)
			return -1;
		return usedCount;
	}

	private static int findMax(int stations[][], int currentIndex, boolean used[]) {
		int max = -1;
		int idxMax = -1;
		for (int k = 0; k < currentIndex; k++) {
			if (!used[k] && stations[k][1] > max) {
				max = stations[k][1];
				idxMax = k;
			}
		}
		return idxMax;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int target = scanner.nextInt();
		int start = scanner.nextInt();
		int n = scanner.nextInt();
		int stations[][] = new int[n ][2];
		for (int k = 0; k < n; k++) {
			stations[k][0] = scanner.nextInt();
			stations[k][1] = scanner.nextInt();
		}

		System.out.println(minRefuelStops(target, start, stations));
		scanner.close();
	}
}
// 100 10 4 10 60 20 30 30 30 60 40 -> 2
// 1 1 0 -> 0
// 100 1 1 10 100 -> -1