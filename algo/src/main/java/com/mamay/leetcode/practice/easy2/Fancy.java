package com.mamay.leetcode.practice.easy2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Fancy {

    ArrayList<Boolean> isAddList = new ArrayList<>();
    ArrayList<Integer> valueList = new ArrayList<>();
    ArrayList<Integer> opList = new ArrayList<>();
    ArrayList<Integer> startList = new ArrayList<>();
    HashMap<Integer, List<Integer>> map = new HashMap<>();

    static int MODULO = 1000000007;

    public Fancy() {

    }

    public void append(int val) {
        startList.add(isAddList.size());
        valueList.add(val);
    }

    public void addAll(int inc) {
        isAddList.add(true);
        opList.add(inc);
    }

    public void multAll(int m) {
        isAddList.add(false);
        opList.add(m);
    }

    public int getIndex(int idx) {
        if (idx >= valueList.size()) return -1;
        BigInteger val = BigInteger.valueOf(valueList.get(idx));
        int start = startList.get(idx);
        if (map.containsKey(idx)) {
            List<Integer> list = map.get(idx);
            start = list.get(0);
            val = BigInteger.valueOf(list.get(1));
        }
        for (int i = start; i < isAddList.size(); i++) {
            boolean isAdd = isAddList.get(i);
            int inc = opList.get(i);
            if (isAdd) val = val.add(BigInteger.valueOf(inc));
            else val = val.multiply(BigInteger.valueOf(inc));
        }
        int res = val.mod(BigInteger.valueOf(MODULO)).intValue();
        map.put(idx, Arrays.asList(isAddList.size(), res));
        return res;
    }

    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(2);
        fancy.addAll(3);
        fancy.append(7);
        fancy.multAll(2);
        fancy.getIndex(0); // return 10
        fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
        fancy.append(10);  // fancy sequence: [13, 17, 10]
        fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
        fancy.getIndex(0); // return 26
        fancy.getIndex(1); // return 34
        fancy.getIndex(2);

        System.out.println(-5 % MODULO);
    }
}