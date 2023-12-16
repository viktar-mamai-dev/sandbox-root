package com.mamay.multithreading.jmp2022.task3;

import com.mamay.multithreading.jmp2022.Stoppable;

public class Consumer extends Stoppable {
    private final DataQueue dataQueue;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        consume();
    }

    public void consume() {
        while (runFlag) {
            Message message;
            if (dataQueue.isEmpty()) {
                try {
                    dataQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            message = dataQueue.remove();
            dataQueue.notifyAllForFull();
            consumeMessage(message);
        }
        System.out.println("Consumer Stopped");
    }

    private void consumeMessage(Message message) {
        if (message != null) {
            System.out.printf("[%s] Consuming Message. Id: %d, Payload: %s\n",
                    Thread.currentThread().getName(),
                    message.getId(),
                    message.getPayLoad());
        }
    }

    public void stop() {
        super.stop();
        dataQueue.notifyAllForEmpty();
    }
}