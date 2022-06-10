package book1.ch4.pond.swan;

import book1.ch4.pond.shore.Bird;

public class Swan extends Bird {
    public void swim() {
        floatWater();
        System.out.println(text);
    }

    public void helpOtherSwanSwim() {
        Swan other = new Swan();
        other.floatWater(); // OK, this Swan class is in the same package as Swan with floatWater method
        System.out.println(other.text);
    }

    public void helpOtherBirdSwam() {
        Bird bird = new Bird();
        bird.floatWater(); // ERROR, we use through var reference bird. Bird is in another package
        System.out.println(bird.text);
    }
}
