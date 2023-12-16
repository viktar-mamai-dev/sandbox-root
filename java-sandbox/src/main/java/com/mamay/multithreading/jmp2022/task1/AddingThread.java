package com.mamay.multithreading.jmp2022.task1;

import com.mamay.multithreading.jmp2022.RandGenerator;
import com.mamay.multithreading.jmp2022.Stoppable;
import com.mamay.multithreading.jmp2022.ThreadUtil;

import java.util.Map;

class AddingThread extends Stoppable {
    private final Map<Integer, Integer> map;

    public AddingThread(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        while (runFlag) {
            for (int i = 0; i < 100; i++) {
                int key = RandGenerator.generateRandomInt();
                //System.out.println("Adding key: " + key);
                map.put(key, RandGenerator.generateRandomInt());
            }
            ThreadUtil.sleep(100);
        }
    }
}