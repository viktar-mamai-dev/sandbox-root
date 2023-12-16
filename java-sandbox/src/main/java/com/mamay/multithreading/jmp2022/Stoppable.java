package com.mamay.multithreading.jmp2022;

/**
 * Simple extension for runnable interface Adding flag runFlag to indicate when to stop thread
 * execution
 */
public abstract class Stoppable implements Runnable {
  protected volatile boolean runFlag = true;

  public void stop() {
    this.runFlag = false;
  }
}
