package com.mamay.task6.entity;

import com.mamay.task6.exception.LogicException;
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

  public void riding() throws LogicException {
    try {
      Thread.sleep(new Random().nextInt(2000));
    } catch (InterruptedException e) {
      throw new LogicException(e);
    }
  }
}
