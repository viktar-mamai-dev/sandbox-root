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
public class Antivirus extends SystemSoft {
  protected int securityLevel;

  public Antivirus(String name, double price, double size, List<String> typeOS, int securityLevel) {
    super(name, price, size, typeOS);
    this.securityLevel = securityLevel;
  }
}
