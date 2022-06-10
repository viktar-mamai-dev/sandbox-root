package com.mamay.task2.entity;

public enum WorkerType {
    OPERATOR, MAKE_UP_MAN, COSTUMER, DESIGNER, ENGINEER, EFFECTS, GRAPHICS;

    public int getMinIncome() {
        return switch (this) {
            case DESIGNER -> 3200;
            case COSTUMER -> 3500;
            case OPERATOR -> 4000;
            case MAKE_UP_MAN -> 3000;
            case EFFECTS -> 4500;
            case GRAPHICS -> 5000;
            case ENGINEER -> 2700;
        };
    }

    public int getMaxIncome() {
        return getMinIncome() * 3;
    }
}
