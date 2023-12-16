package com.mamay.inspection.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Magazine extends Entity {
  private static final long serialVersionUID = -2824763500938665004L;

  private String title;
  private String annotation;
  private Period period;
  private String location;
  private ArrayList<Subscription> subs = new ArrayList<Subscription>();

  public Magazine(String title, String annotation) {
    this.title = title;
    this.annotation = annotation;
  }

  public Magazine(int id, String title, String annotation) {
    super(id);
    this.title = title;
    this.annotation = annotation;
  }

  public void addSubscription(Subscription sub) {
    this.subs.add(sub);
  }

  public void addSubscriptions(List<Subscription> subList) {
    this.subs.addAll(subList);
  }

  public List<Subscription> getSubs() {
    return Collections.unmodifiableList(subs);
  }
}
