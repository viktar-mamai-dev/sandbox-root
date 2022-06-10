package com.mamay.leetcode.contest.weeks_100_110.week101;

import java.util.ArrayList;
import java.util.List;

public class StockSpanner {

	private List<Integer> prices = new ArrayList<Integer>();
	private List<Integer> values = new ArrayList<>();

	public StockSpanner() {

	}

	public int next(int price) {
		int len = prices.size();
		if (len == 0) {
			prices.add(price);
			values.add(1);
			return 1;
		}

		int value = 1;
		int j = len - 1;
		while (j >= 0) {
			if (prices.get(j) <= price) {
				int prevValue = values.get(j);
				value += prevValue;
				j -= prevValue;
			} else {
				break;
			}
		}
		prices.add(price);
		values.add(value);
		return value;
	}
}
