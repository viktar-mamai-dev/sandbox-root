package com.mamay.leetcode.contest.weeks_90_100.week91;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Shortest {

	public static int shortestSubarray(int[] A, int K) {
		int len = A.length;
		int start = 0;

		int minLen = len + 1;
		int sumHere = 0, lengthHere = 0;
		for (int i = 0; i < len; i++) {
			sumHere += A[i];
			if (sumHere >= K) {
				lengthHere = i - start + 1;
				if (lengthHere < minLen) {
					minLen = lengthHere;
				}
				i = start;
				start++;
				sumHere = 0;
			}
			if (sumHere < 0) {
				start = i + 1;
				sumHere = 0;
			}
		}
		return minLen > len ? -1 : minLen;
	}

	public static int shortestSubarray22(int[] A, int K) {
		int n = A.length;
		int currSum = 0, minLen = n + 1;

		int start = 0, end = 0;
		while (end <= n) {

			while(A[start] <= 0)
				currSum -= A[start++];
			
			while (currSum < K && end < n) {
				if (currSum <= 0) {
					start = end;
					currSum = 0;
				}
				currSum += A[end++];
			}

			// If current sum becomes greater than x.
			while (currSum >= K && start < n) {
				// Update minimum length if needed
				if (end - start < minLen)
					minLen = end - start;
				
				if(minLen == 1)
					return 1;

				// remove starting elements
				currSum -= A[start++];
			}
		}
		return minLen > n ? -1 : minLen;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("input.txt"));
		int k = scanner.nextInt();

		String str[] = scanner.next().split(",");
		int len = str.length;
		int bills[] = new int[len];
		for (int i = 0; i < len; i++) {
			bills[i] = Integer.parseInt(str[i]);
		}
		long time = System.currentTimeMillis();
		System.out.println(shortestSubarray22(bills, k));
		System.out.println("Time: " + (System.currentTimeMillis() - time));
		scanner.close();
	}
}
