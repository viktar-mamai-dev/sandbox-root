package com.mamay.core.pack1;

public class Dumber extends Dumb {

    int x = 9;

    public Dumber() {
        this.x = this.getX() + 10;
        System.out.println(x);
    }

    public int getX() {
        return x;
    }
}
