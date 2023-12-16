package com.mamay.task7.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Archiver extends SystemSoft {
  private String type;

  public Archiver(String name, double price, double size, List<String> typeOS, String type) {
    super(name, price, size, typeOS);
    this.type = type;
  }
}
