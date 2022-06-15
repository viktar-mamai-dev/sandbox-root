package book2.ch3;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparaTester {
    public static void main(String[] args) {
        MyClass1 m1 = new MyClass1();
        System.out.println(m1 instanceof Comparable); // TRUE
        TreeSet<MyClass1> set = new TreeSet<>(); // via Comparable
        set.add(m1); // ClassCastException when class does not implements Comparable

        MyClass1 m2 = new MyClass1();
        set.add(m2);
        System.out.println(set);// [m1. m2]

        TreeSet<MyClass1> setReverse = new TreeSet<>(m1); // via Comparator
        setReverse.add(m1);
        setReverse.add(m2);
        System.out.println(setReverse); // reverse [m2. m1]
    }
}

// interfaces are inherited
class MyClass1 extends MyClass2 {

}

// possible to implement both
class MyClass2 implements Comparable<MyClass2>, Comparator<MyClass2> {

    @Override
    public int compareTo(MyClass2 o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public int compare(MyClass2 o1, MyClass2 o2) {
        return o2.hashCode() - o1.hashCode();
    }
}