package book1.ch5;

public interface Herbivore {
    private int amount = 10;

    public static void eatGrass(); // static must have body

    public int chew() { // default modifier required
        return 13;
    }
}
