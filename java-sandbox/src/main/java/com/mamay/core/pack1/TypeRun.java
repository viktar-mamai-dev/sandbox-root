package com.mamay.core.pack1;

/**
 * Created by admin on 9/29/2014.
 */
public class TypeRun {
    public static void main(String[] args) {
        TypeStyle ts = TypeStyle.AIR;
        TypeStyle ts1 = TypeStyle.valueOf("earth".toUpperCase());
        String name = ts.name();
        int poos = ts.ordinal();
    }
}
