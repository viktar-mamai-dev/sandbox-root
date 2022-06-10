package com.mamay.leetcode.practice.easy1;

import java.util.HashMap;

public class Isomorphic_205 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> mapFrom = new HashMap<>();
        HashMap<Character, Character> mapTo = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            Character value = mapFrom.get(ch1);
            if(value == null) {
                mapFrom.put(ch1, ch2);
            } else if (value != ch2) {
                return false;
            }

            value = mapTo.get(ch2);
            if(value == null) {
                mapTo.put(ch2, ch1);
            } else if (value != ch1){
                return false;
            }
        }
        return true;
    }
}
