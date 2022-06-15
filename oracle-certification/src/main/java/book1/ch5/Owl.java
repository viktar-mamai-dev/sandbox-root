package book1.ch5;

interface Nocturnal {
    default boolean isBlind() {
        System.out.println(this);
        System.out.println("Nocturnal");
        return true;
    }
}

public class Owl implements Nocturnal{
    public boolean isBlind() {
        System.out.println(this);
        System.out.println("Owl");
        return false;
    }

    public static void main(String[] args) {
        Nocturnal nocturnal = (Nocturnal) new Owl();
        System.out.println(nocturnal.isBlind()); // false - method from Owl
    }
}
