package com.mamay.core.pack3;

/**
 * Created by admin on 9/26/2014.
 */
public class MessageRunner {
    public static void main(String[] args) {
        Message<Integer> message = new Message<>(123, "hello");
        Message<Integer> message0 = new Message<>(123, "hell");
        Message<Double> message1 = new Message<>(123.567, "velcom");
        int id = message.getMessageId();
        double id1 = message1.getMessageId();
        Id obj = new Id(new Byte("1"));

        obj.counter();
        obj.counter(1);
        obj.counter(1, 2);
        int[] arr = {8, 7, 6, 5, 4};
        obj.counter(arr);
        Message<Id> message2 = new Message<>(obj, "velcom");
        Id temp = message2.getMessageId();
        if (message instanceof Message) {
        }

        message.equalsMessageById(message0);
        message.equalsMessageById(message2);
        System.out.println("ok");
    }
}
