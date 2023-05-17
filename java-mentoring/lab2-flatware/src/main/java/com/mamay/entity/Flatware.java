package com.mamay.entity;

import com.mamay.type.BladeType;
import com.mamay.type.FlatwareType;
import com.mamay.type.HelveType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Flatware {
  private String origin;
  private boolean value; // optional
  private FlatwareType flatware;
  private Visual visual; // optional

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Visual {
    private int bladeLength;
    private int bladeWidth;
    private int prongLength;
    private BladeType blade;
    private HelveType helve;
  }
}
