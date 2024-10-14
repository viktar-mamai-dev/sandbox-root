package com.mamay;

public class DoubleComparison {
  public static void main(String[] args) {

    Double x = Double.NaN;
    Double y = Double.NaN;
    double a = x.doubleValue();
    double b = y.doubleValue();

    System.out.println(a == b);
    System.out.println(x.equals(y));

    x = new Double(+0.0);
    y = new Double(-0.0);
    a = x.doubleValue();
    b = y.doubleValue();
    System.out.println(a == b);
    System.out.println(x.equals(y));
  }
}
