package com.mamay.task1;

public class A {

    private B instance;

    private static int iteration;

    public A() {
        this(0);
    }

    public A(int iteration) {
        A.iteration = iteration;
        instance = new B(++iteration);
    }

    public static int getIteration() {
        return iteration;
    }

}
