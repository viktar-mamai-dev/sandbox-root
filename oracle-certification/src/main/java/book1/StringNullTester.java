package book1;

import book1.ch5.Owl;

import static java.lang.System.out;

public class StringNullTester {
    public static void main(String[] args) {
        out.println(null + 1);
        out.println(true + false);
        out.println(getNull() + 1); // null1

        Integer i1 = new Integer(10);
        Long l1 = 10l;
        Double d1 = new Double(10);
        Number f1 = 10;
        int i2 = 10;
        long l2 = 10;
        double d2 = 10.0;
        float f2 = 10.0f;
        IndexOutOfBoundsException exc1 = new ArrayIndexOutOfBoundsException();
        StringIndexOutOfBoundsException exc2 = new StringIndexOutOfBoundsException();
        out.println((i2 == d2) + " " + (l2 == f2) + " " + (f1 == l1) + " : " + (exc1 == exc2)); // true true false

        Owl owl = new Owl();
    }

    private static String getNull() {
        return null;
    }
}
