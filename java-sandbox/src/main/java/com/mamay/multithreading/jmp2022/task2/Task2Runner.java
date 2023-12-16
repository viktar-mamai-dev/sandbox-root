package com.mamay.multithreading.jmp2022.task2;

import com.mamay.multithreading.jmp2022.RandGenerator;
import com.mamay.multithreading.jmp2022.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

public class Task2Runner {

    public static void main(String[] args) {
        final List<Integer> list = RandGenerator.generateRandomList(0);

        WritingThread writingThread = new WritingThread(list);
        CalculatingThread calculatingThread = new CalculatingThread(list);
        SummingThread summingThread = new SummingThread(list);

        List<Thread> threads = new ArrayList<>(3);
        threads.add(new Thread(writingThread));
        threads.add(new Thread(calculatingThread));
        threads.add(new Thread(summingThread));

        ThreadUtil.start(threads);

        ThreadUtil.sleep(100);

        // signal to end processing
        writingThread.stop();
        calculatingThread.stop();
        summingThread.stop();

        // wait for completion
        ThreadUtil.join(threads);
    }
}