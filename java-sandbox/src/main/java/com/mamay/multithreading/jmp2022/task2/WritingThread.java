package com.mamay.multithreading.jmp2022.task2;

import com.mamay.multithreading.jmp2022.RandGenerator;
import com.mamay.multithreading.jmp2022.Stoppable;

import java.util.List;

public class WritingThread extends Stoppable {
    private final List<Integer> list;

    public WritingThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        // we can't apply infinite loop as while (true) here as we get OutOfMemoryError some time
        while (runFlag) {
            synchronized (list) { // sync on list
                Integer val = RandGenerator.generateRandomInt();
                System.out.println("Adding: " + val);
                list.add(val);
            }
        }
    }
}
