package com.mamay.core.pack1;

public class Interface1 {

    public static void main(String[] args) {
        Formula formula1 = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        formula1.calculate(100);     // 100.0
        formula1.sqrt(-23);          // 0.0
        Formula.positive(-4);        // 0.0
    }

    interface Formula {
        static int positive(int a) {
            return Math.max(a, 0);
        }

        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(positive(a));
        }
    }

}