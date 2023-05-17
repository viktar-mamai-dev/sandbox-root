package com.mamay.leetcode.practice.medium1;

class ATM {
    private final int SIZE = 5;
    private final int[] banknotes = {20, 50, 100, 200, 500};
    private int[] banknotesCount = new int[SIZE];

    public ATM() {

    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < SIZE; i++) {
            this.banknotesCount[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] withdrawCount = new int[SIZE];
        for (int i = SIZE - 1; i >= 0; i--) {
            int banknotesCountToWithdraw = Math.max(banknotesCount[i], amount / banknotes[i]);
            if (banknotesCountToWithdraw == 0) continue;
            amount -= banknotes[i] * banknotesCountToWithdraw;
            banknotesCount[i] -= banknotesCountToWithdraw;
            withdrawCount[i] += banknotesCountToWithdraw;
            if (amount == 0) return withdrawCount;
        }
        this.deposit(withdrawCount);
        return new int[]{-1};
    }
}
