package com.mamay.inspection.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reservation extends Entity {
  private static final long serialVersionUID = 8409441552942455L;

  private User user;
  private Subscription subscription;
  private Status status;
  private int count;

  public Reservation(int id, int count) {
    super(id);
    this.count = count;
  }

  public Reservation(int count) {
    this.count = count;
  }
}
