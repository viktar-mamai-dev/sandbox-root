package patterns.observer;

import java.util.ArrayList;

public class Channel implements Subject {
  private ArrayList<Observer> observers;
  private String channelName;
  private String status;

  public Channel(String channelName) {
    this.channelName = channelName;
    this.observers = new ArrayList<>();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
    notifyObservers();
  }

  @Override
  public void registerObserver(Observer observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    this.observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    this.observers.forEach(observer -> observer.update(getStatus()));
  }
}
