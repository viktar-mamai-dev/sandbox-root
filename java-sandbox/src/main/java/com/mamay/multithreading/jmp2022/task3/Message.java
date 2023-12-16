package com.mamay.multithreading.jmp2022.task3;

public class Message {
    private final int id;
    private final String payLoad;

    public Message(int id, String payLoad) {
        this.id = id;
        this.payLoad = payLoad;
    }

    public int getId() {
        return id;
    }

    public String getPayLoad() {
        return payLoad;
    }
}