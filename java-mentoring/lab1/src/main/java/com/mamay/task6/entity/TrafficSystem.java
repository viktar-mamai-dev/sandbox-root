package com.mamay.task6.entity;

import com.mamay.task6.exception.LogicException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TrafficSystem {
  private Queue<Tunnel> tunnels = new LinkedList<Tunnel>();
  private Semaphore semaphore;

  public TrafficSystem(Queue<Tunnel> t) {
    tunnels.addAll(t);
    semaphore = new Semaphore(t.size(), true);
  }

  public void addTunnel(Tunnel t) {
    tunnels.add(t);
  }

  public Tunnel getTunnel(long millis) throws LogicException {
    try {
      if (semaphore.tryAcquire(new Random().nextInt(1000), TimeUnit.MILLISECONDS)) {
        Tunnel t = tunnels.poll();
        if (t != null) {
          return t;
        } else {
          throw new LogicException("List of tunnels is empty");
        }
      }
    } catch (InterruptedException e) {
      throw new LogicException(e);
    }
    throw new LogicException("Time is over");
  }

  public void release(Tunnel t) {
    tunnels.add(t);
    semaphore.release();
  }
}
