package com.mamay.inspection.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Status extends Entity {
  private static final long serialVersionUID = 2347517611650124975L;

  private String description;

  public Status(String description) {
    this.description = description;
  }

  public Status(int id, String description) {
    super(id);
    this.description = description;
  }
}
