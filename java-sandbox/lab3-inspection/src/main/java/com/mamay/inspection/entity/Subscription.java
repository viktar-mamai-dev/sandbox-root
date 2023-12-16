package com.mamay.inspection.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Subscription extends Entity {
  private static final long serialVersionUID = -1046895540428914609L;

  private Magazine magazine;
  private String index;
  private String duration;
  private String price;

  public Subscription(String index, String duration, String price) {
    super();
    this.index = index;
    this.duration = duration;
    this.price = price;
  }

  public Subscription(int id, String index, String duration, String price) {
    super(id);
    this.index = index;
    this.duration = duration;
    this.price = price;
  }
}
