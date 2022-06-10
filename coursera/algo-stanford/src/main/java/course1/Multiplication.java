package course1;

import java.math.BigDecimal;

public class Multiplication {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal("3141592653589793238462643383279502884197169399375105820974944592");
        BigDecimal d2 = new BigDecimal("2718281828459045235360287471352662497757247093699959574966967627");
        System.out.println(d1.multiply(d2));
    }
}
