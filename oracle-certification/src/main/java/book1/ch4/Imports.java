package book1.ch4;

import java.util.Arrays;

public class Imports {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3);

        //fly1(1, 3, 4); COMPILE-ERROR - varargs can't be passed when array expected
        fly2(new int[]{1, 2, 3}); // but vice versa is possible
    }

    static void fly1(int[] arr) {

    }

    static /* possible to change modifiers order*/ public void fly2(int... varargs) {

    }
}

