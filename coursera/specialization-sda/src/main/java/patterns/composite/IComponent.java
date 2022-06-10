package patterns.composite;

public interface IComponent {
    void play();

    void setPlaybackSpeed(float speed);

    String getName();
}
