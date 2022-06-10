package com.mamay.leetcode.contest.weeks_80_90.week84;

public class FindReplace {

	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		String str = S;
		int n = indexes.length;
		for (int i = 0; i < n; i++) {
			String prefix = S.substring(0, indexes[i]);
			String postFix = S.substring(indexes[i]);
			if (postFix.startsWith(sources[i])) {
				postFix = postFix.replaceFirst(sources[i], targets[i]);
				str = prefix + postFix;
			}
		}
		return str;
	}
}
