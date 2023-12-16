package com.mamay.task5;

import com.mamay.Lab1Exception;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ChannelPool {
  private static final int POOL_SIZE = 5;
  private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
  private final Queue<AudioChannel> resources = new LinkedList<AudioChannel>();

  public ChannelPool(Queue<AudioChannel> source) {
    resources.addAll(source);
  }

  public AudioChannel getResource(long maxWaitMillis) throws Lab1Exception {
    try {
      if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
        return resources.poll();
      }
    } catch (InterruptedException e) {
      throw new Lab1Exception(e);
    }
    throw new Lab1Exception("Semaphore issue");
  }

  public void returnResource(AudioChannel res) {
    resources.add(res);
    semaphore.release();
  }
}
