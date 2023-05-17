package com.mamay.leetcode.practice.easy3;

import java.util.HashSet;

public class UniqueEmail_929 {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();
        for (String s : emails) {
            int dotIdx = s.indexOf('@');
            String localName = s.substring(0, dotIdx);
            String domainName = s.substring(dotIdx);
            String s1 = localName.replaceAll("\\.", "");
            int plusIdx = s1.indexOf('+');
            if (plusIdx != -1) {
                s1 = s1.substring(0, plusIdx);
            }
            String s3 = s1 + domainName;
            set.add(s3);
        }
        return set.size();
    }
}
