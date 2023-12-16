package patterns.observer;

public class Follower implements Observer {
  private String followername;

  public Follower(String followername) {
    this.followername = followername;
  }

  @Override
  public void update(String status) {
    if (status.equalsIgnoreCase("Active")) {
      this.play();
    }
  }

  public void play() {
    System.out.println("Playing channel for the follower=" + this.followername);
  }
}
