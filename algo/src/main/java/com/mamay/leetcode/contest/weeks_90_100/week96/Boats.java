package com.mamay.leetcode.contest.weeks_90_100.week96;

public class Boats {

	public static int numRescueBoats(int[] people, int limit) {
		int n = people.length;
		int count = 0;
		boolean used[] = new boolean[n];
		int usedCount = 0;
		int i = 0;
		while (usedCount < n) {
			while (i < n && used[i])
				i++;

			usedCount++;
			count++;
			int val = people[i];
			int maxSum = val;
			used[i++] = true;

			if (i < n) {
				int idxMax = -1;
				for (int k = i; k < n; k++) {
					int sum = val + people[k];
					if (!used[k] && sum <= limit && sum > maxSum) {
						maxSum = sum;
						idxMax = k;
					}
				}
				if (idxMax != -1) {
					used[idxMax] = true;
					usedCount++;
				}

			}
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(numRescueBoats(new int[] { 3, 5, 3, 4 }, 5));
	}
}

