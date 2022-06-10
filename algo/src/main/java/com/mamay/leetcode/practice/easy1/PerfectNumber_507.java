package com.mamay.leetcode.practice.easy1;

public class PerfectNumber_507 {
    public boolean checkPerfectNumber(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if(i* i != num) {
                    sum += num / i;
                }
            }
        }

        return sum == num;
    }
}
