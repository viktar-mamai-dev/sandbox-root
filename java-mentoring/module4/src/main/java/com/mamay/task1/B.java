package com.mamay.task1;

public class B {

    private A instance = null;

    public B(int iteration) {
        instance = new A(++iteration);
    }
}
