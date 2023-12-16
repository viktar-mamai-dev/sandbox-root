package com.mamay;

import com.mamay.task6.TrafficSystem;
import com.mamay.task6.Train;
import com.mamay.task6.Tunnel;
import java.util.LinkedList;
import java.util.Random;

public class Task6Runner {
  public static void main(String[] args) {
    Random rand = new Random();
    LinkedList<Tunnel> list = new LinkedList<Tunnel>();
    int count = rand.nextInt(10) + 5;
    for (int i = 0; i < count; i++) {
      list.add(new Tunnel("Tunnel#" + i));
    }
    TrafficSystem trafficSystem = new TrafficSystem(list);

    count = rand.nextInt(10) + 5;
    for (int i = 0; i < count; i++) {
      Train t = new Train(trafficSystem, "train#" + i);
      t.start();
    }
  }
}
