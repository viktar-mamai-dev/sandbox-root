package com.mamay.task5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ChannelPool {
    private final static int POOL_SIZE = 5;
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private final Queue<AudioChannel> resources = new LinkedList<AudioChannel>();

    public ChannelPool(Queue<AudioChannel> source) {
        resources.addAll(source);
    }

    public AudioChannel getResource(long maxWaitMillis) throws ResourceException {
        try {
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                return resources.poll();
            }
        } catch (InterruptedException e) {
            throw new ResourceException(e);
        }
        throw new ResourceException("Semaphore issue");
    }

    public void returnResource(AudioChannel res) {
        resources.add(res);
        semaphore.release();
    }
}
