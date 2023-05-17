/*
 * Copyright (c) 2023
 */
package book1.ch5;

public abstract class Bird {
  private void fly() {
    System.out.println("Bird");
  }

  public static void main(String[] args) {
    Bird bird = new Pelican();
    // will invoke parent method, as it is hidden from child
    bird.fly(); // private accessible as we are in the same class,

    Pelican pelican = new Pelican();
    pelican.fly(); // child method Pelican
  }
}

class Pelican extends Bird {

  // @Override // without Override - possible, but with - private method => COMPILE-ERROR
  protected void fly() {
    System.out.println("Pelican");
  }
}
