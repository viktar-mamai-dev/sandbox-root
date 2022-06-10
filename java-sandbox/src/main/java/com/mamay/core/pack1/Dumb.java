package com.mamay.core.pack1;

public class Dumb {
    int x = -7;

    public Dumb() {
        this.x = this.getX() + 1;
        System.out.println(x);
    }

    public int getX() {
        return x;
    }
}
