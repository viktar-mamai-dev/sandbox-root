package com.mamay.task6;

import com.mamay.Lab1Exception;
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

  public Tunnel getTunnel(long millis) throws Lab1Exception {
    try {
      if (semaphore.tryAcquire(new Random().nextInt(1000), TimeUnit.MILLISECONDS)) {
        Tunnel t = tunnels.poll();
        if (t != null) {
          return t;
        } else {
          throw new Lab1Exception("List of tunnels is empty");
        }
      }
    } catch (InterruptedException e) {
      throw new Lab1Exception(e);
    }
    throw new Lab1Exception("Time is over");
  }

  public void release(Tunnel t) {
    tunnels.add(t);
    semaphore.release();
  }
}
