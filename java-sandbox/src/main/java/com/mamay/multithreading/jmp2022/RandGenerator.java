package com.mamay.multithreading.jmp2022;

import java.util.*;

public class RandGenerator {
    private final static Random RANDOM = new Random();

    private final static int LOWER_BOUND = 1;
    private final static int UPPER_BOUND = 1_000_000;

    public static Integer generateRandomInt() {
        return RANDOM.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
    }

    public static Map<Integer, Integer> generateRandomMap(int size) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(generateRandomInt(), generateRandomInt());
        }
        return map;
    }

    public static List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(generateRandomInt());
        }
        return list;
    }

    public static String generateRandomString() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 10; i++) { // 10 symbols
            char baseCh = RANDOM.nextBoolean() ? 'a' : 'A'; // uppercase or lowercase
            b.append((char) (baseCh + RANDOM.nextInt(26))); // append random char
        }
        return b.toString();
    }
}
