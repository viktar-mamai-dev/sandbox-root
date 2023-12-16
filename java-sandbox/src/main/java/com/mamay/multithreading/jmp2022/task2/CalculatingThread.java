package com.mamay.multithreading.jmp2022.task2;

import com.mamay.multithreading.jmp2022.Stoppable;

import java.util.List;

public class CalculatingThread extends Stoppable {

    private final List<Integer> list;

    public CalculatingThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (runFlag) {
            synchronized (list) {  // sync on list
                double sumOfSquares = list.stream().mapToDouble(val -> (double) val * val).sum();
                System.out.println("Square root of sum of squares: " + (long) Math.sqrt(sumOfSquares));
            }
        }
    }
}
