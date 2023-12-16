package com.mamay.multithreading.jmp2022.task3;

import com.mamay.multithreading.jmp2022.RandGenerator;
import com.mamay.multithreading.jmp2022.Stoppable;

public class Producer extends Stoppable {
  private final DataQueue dataQueue;

  private static int idSequence = 0;

  public Producer(DataQueue dataQueue) {
    this.dataQueue = dataQueue;
  }

  @Override
  public void run() {
    produce();
  }

  public void produce() {
    while (runFlag) {
      Message message = produceMessage();
      while (dataQueue.isFull()) {
        try {
          dataQueue.waitOnFull();
        } catch (InterruptedException e) {
          e.printStackTrace();
          break;
        }
      }
      if (!runFlag) {
        break;
      }
      dataQueue.add(message);
      dataQueue.notifyAllForEmpty();
    }
    System.out.println("Producer Stopped");
  }

  private Message produceMessage() {
    Message message = new Message(++idSequence, RandGenerator.generateRandomString());
    System.out.printf(
        "[%s] Generated Message. Id: %d, Payload: %s\n",
        Thread.currentThread().getName(), message.getId(), message.getPayLoad());

    return message;
  }

  public void stop() {
    super.stop();
    dataQueue.notifyAllForFull();
  }
}
