package patterns.composite;

public class Song implements IComponent {
  public String songName;
  public String artist;
  public float speed;

  public Song(String songName, String artist) {
    this.songName = songName;
    this.artist = artist;
    this.speed = 1.0F;
  }

  @Override
  public void play() {
    System.out.println(
        "Playing song="
            + this.songName
            + " [artist="
            + this.artist
            + "] with a speed="
            + this.speed);
  }

  @Override
  public void setPlaybackSpeed(float speed) {
    this.speed = speed;
  }

  @Override
  public String getName() {
    return this.songName;
  }

  public String getArtist() {
    return this.artist;
  }
}
