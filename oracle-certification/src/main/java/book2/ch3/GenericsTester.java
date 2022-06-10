package book2.ch3;

import java.util.ArrayList;
import java.util.List;

public class GenericsTester {
    public static void main(String[] args) {
        List<?> list1 = new ArrayList<A>();
        List<? extends A> list2 = new ArrayList<A>();
        List<? super A> list3 = new ArrayList<A>();
        List<? extends B> list4 = new ArrayList<C>();
        List<? super B> list5 = new ArrayList<A>();
        List<?> list6 = new ArrayList<C>();

        ArrayList<Double> al = new ArrayList<>();
        al.contains("traviata"); // FALSE, but possible
    }

    <T> T method1(List<? extends T> list) { // OK
        return list.get(0);
    }

    <T> A method2(List<? extends A> list) { // OK
        return list.get(0);
    }

    <B extends A> B method3(List<B> list) {
        return list.get(0);
    }
}

class A {
}

class B extends A {
}

class C extends B {
}
