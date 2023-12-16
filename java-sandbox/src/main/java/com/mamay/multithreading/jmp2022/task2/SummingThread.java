package com.mamay.multithreading.jmp2022.task2;

import com.mamay.multithreading.jmp2022.Stoppable;

import java.util.List;

public class SummingThread extends Stoppable {

    private final List<Integer> list;

    public SummingThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (runFlag) {
            synchronized (list) { // sync on list
                System.out.println("Sum: " + list.stream().mapToInt(val -> val).sum());
            }
        }
    }
}
