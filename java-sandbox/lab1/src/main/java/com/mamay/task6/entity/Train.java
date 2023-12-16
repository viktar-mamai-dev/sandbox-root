package com.mamay.task6.entity;

import com.mamay.task6.exception.LogicException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Train extends Thread {
  private boolean ride = false;
  private TrafficSystem system;
  private String trainName;

  public Train(TrafficSystem s, String n) {
    system = s;
    this.trainName = n;
  }

  @Override
  public void run() {
    Tunnel t = null;
    try {
      t = system.getTunnel(500);
      ride = true;
      log.debug("Train " + trainName + " rides in tunnel " + t.getName());
      t.riding();
    } catch (LogicException e) {
      log.error(e.getMessage());
      log.error("Train " + trainName + " got lost");
    } finally {
      if (t != null) {
        ride = false;
        log.debug(trainName + " finished " + t.getName());
        system.release(t);
      }
    }
  }

  public boolean isRide() {
    return ride;
  }

  public String getTrainName() {
    return trainName;
  }
}
