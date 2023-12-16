package com.mamay.task6;

import com.mamay.Lab1Exception;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tunnel {
  private String name;

  public void riding() throws Lab1Exception {
    try {
      Thread.sleep(new Random().nextInt(2000));
    } catch (InterruptedException e) {
      throw new Lab1Exception(e);
    }
  }
}
