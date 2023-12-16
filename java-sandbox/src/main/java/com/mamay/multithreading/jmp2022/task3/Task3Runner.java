package com.mamay.multithreading.jmp2022.task3;

import com.mamay.multithreading.jmp2022.ThreadUtil;
import java.util.ArrayList;
import java.util.List;

public class Task3Runner {
  private static final int MAX_QUEUE_CAPACITY = 5;

  // configure the number of producers and consumers
  private static final int PRODUCER_COUNT = 3;
  private static final int CONSUMER_COUNT = 3;

  public static void demoMultipleProducersAndMultipleConsumers() {
    DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);

    List<Thread> threads = new ArrayList<>();
    Producer producer = new Producer(dataQueue);
    for (int i = 0; i < PRODUCER_COUNT; i++) {
      threads.add(new Thread(producer));
    }
    Consumer consumer = new Consumer(dataQueue);
    for (int i = 0; i < CONSUMER_COUNT; i++) {
      threads.add(new Thread(consumer));
    }

    ThreadUtil.start(threads);
    // pause
    ThreadUtil.sleep(100);

    // Stop threads
    producer.stop();
    consumer.stop();

    ThreadUtil.join(threads);
  }

  public static void main(String[] args) {
    demoMultipleProducersAndMultipleConsumers();
  }
}
