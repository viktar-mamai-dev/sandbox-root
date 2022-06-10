package book1.ch4.pond.goose;

import book1.ch4.pond.shore.Bird;

public class Goose extends Bird {
    public void helpGooseSwim() {
        Goose other = new Goose();
        other.floatWater();
        System.out.println(other.text);
    }

    public void helpOtherGooseSwim() {
        Bird other = new Goose();
        other.floatWater(); // Bird is in another package. Checks are for reference type
        System.out.println(other.text);

        System.out.println(this.text);
    }
}
