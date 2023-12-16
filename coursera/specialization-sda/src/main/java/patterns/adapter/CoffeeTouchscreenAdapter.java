package patterns.adapter;

public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {
  private final OldCoffeeMachine machine;

  public CoffeeTouchscreenAdapter(OldCoffeeMachine machine) {
    this.machine = machine;
  }

  public void chooseFirstSelection() {
    this.machine.selectA();
  }

  public void chooseSecondSelection() {
    this.machine.selectB();
  }
}
