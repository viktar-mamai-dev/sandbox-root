package com.mamay.multithreading.jmp2022.task1;

import com.mamay.multithreading.jmp2022.RandGenerator;
import com.mamay.multithreading.jmp2022.Stoppable;
import com.mamay.multithreading.jmp2022.ThreadUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Task1Runner {

    private final static Map<Integer, Integer> TEST_MAP = RandGenerator.generateRandomMap(1_000);

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>(TEST_MAP);
        System.out.println("Running HashMap. Expected CMException: ");
        doJob(new AddingThread(map), new SummingThread(map));

        map = Collections.synchronizedMap(TEST_MAP);
        System.out.println("Running Sync map. Without sync - expected CMException: ");
        doJob(new AddingThread(map), new SummingThread(map));

        map = Collections.synchronizedMap(TEST_MAP);
        System.out.println("Running Sync map. Need to synchronize (read docs) - without Exception: ");
        doJob(new AddingThread(map), new SummingSyncThread(map));

        map = new ConcurrentHashMap<>(TEST_MAP);
        System.out.println("Running ConcurrentHashMap. Expected Passed without Exception: ");
        doJob(new AddingThread(map), new SummingThread(map));

        map = new ThreadSafeMap(TEST_MAP);
        System.out.println("Running Custom thread safe map. Expected Passed without Exception: ");
        doJob(new AddingThread(map), new SummingSyncThread(map));
    }

    private static void doJob(Stoppable summingThread, Stoppable addingThread) {
        List<Thread> threads = new ArrayList<>(2);
        threads.add(new Thread(summingThread));
        threads.add(new Thread(addingThread));
        try {
            ThreadUtil.start(threads);

            ThreadUtil.sleep(2000);

            // signal to end threads
            summingThread.stop();
            addingThread.stop();
        } finally {
            ThreadUtil.join(threads);
        }
    }
}
