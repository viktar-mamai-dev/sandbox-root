package com.mamay.leetcode.contest.weeks_90_100.week91;

import java.util.EnumMap;
import java.util.Scanner;

public class Lemonade {

	private static enum Bill {
		FIVE, TEN, FIF;
	}

	public static boolean lemonadeChange(int[] bills) {
		boolean res = true;

		EnumMap<Bill, Integer> cash = new EnumMap<>(Bill.class);
		cash.put(Bill.FIVE, 0);
		cash.put(Bill.TEN, 0);
		cash.put(Bill.FIF, 0);

		for (int bill : bills) {
			if (bill == 5) {
				Integer count = cash.get(Bill.FIVE);
				cash.put(Bill.FIVE, count + 1);
			} else if (bill == 10) {
				Integer fiveCount = cash.get(Bill.FIVE);
				if (fiveCount == 0) {
					return false;
				}
				cash.put(Bill.FIVE, fiveCount - 1);

				Integer tenCount = cash.get(Bill.TEN);
				cash.put(Bill.TEN, tenCount + 1);
			} else if (bill == 20) {
				Integer fiveCount = cash.get(Bill.FIVE);
				if (fiveCount == 0) {
					return false;
				}
				Integer tenCount = cash.get(Bill.TEN);
				if (tenCount == 0 && fiveCount < 3) {
					return false;
				}
				if (tenCount >= 1) {
					cash.put(Bill.TEN, tenCount - 1);
				} else if (fiveCount >= 3) {
					cash.put(Bill.FIVE, fiveCount - 2);
					fiveCount = cash.get(Bill.FIVE);
				}

				cash.put(Bill.FIVE, fiveCount - 1);
				Integer fifCount = cash.get(Bill.FIF);
				cash.put(Bill.FIF, fifCount + 1);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int bills[] = new int[n];
		for (int i = 0; i < n; i++) {
			bills[i] = scanner.nextInt();
		}
		System.out.println(lemonadeChange(bills));
		scanner.close();
	}
}
