package com.mamay.hackerrank.contest.codeagon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Bob {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long arr[] = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		long sum = solve2(arr, n);
		System.out.println(sum);
		scanner.close();
	}
	
	public static int solve3(long[] A, int n) {
        Set<Long> ans = new HashSet();
        Set<Long> cur = new HashSet();
        cur.add(0l);
        for (long x: A) {
            Set<Long> cur2 = new HashSet();
            for (long y: cur)
                cur2.add(x | y);
            cur2.add(x);
            cur = cur2;
            ans.addAll(cur);
        }

        return ans.size();
    }

	private static long solve2(long[] arr, int n) {
		ArrayList<Long> ans = new ArrayList<>();
		ArrayList<Long> cur = new ArrayList<>();
		//cur.add(0l);
		for (long x : arr) {
			ArrayList<Long> cur2 = new ArrayList<>();
			for (long y : cur)
				cur2.add(x | y);
			cur2.add(x);
			cur = cur2;
			ans.addAll(cur);
		}
		return sum(ans);
	}

	private static long solve(long[] arr, int n) {
		ArrayList<Long> dp[] = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			ArrayList<Long> list = new ArrayList<Long>();
			long val = arr[i];
			list.add(val);
			for (int k = 0; k < i; k++) {
				ArrayList<Long> subList = dp[k];
				for (Long subValue : subList) {
					list.add(val | subValue);
				}
			}
			System.out.println(list);
			dp[i] = list;
		}

		return sum(dp[n - 1]);
	}

	private static long sum(ArrayList<Long> list) {
		long sum = list.stream().reduce(0l, (v1, v2) -> v1 + v2);
		return sum;
	}
}
