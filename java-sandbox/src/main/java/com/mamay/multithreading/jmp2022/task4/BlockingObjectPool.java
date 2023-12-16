package com.mamay.multithreading.jmp2022.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class BlockingObjectPool {
    private final BlockingQueue<Object> queue;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.queue = new ArrayBlockingQueue<>(size);
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() {
        try {
            return this.queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(Object object) {
        try {
            this.queue.put(object);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}