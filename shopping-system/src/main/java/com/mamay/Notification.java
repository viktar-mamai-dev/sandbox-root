package com.mamay;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Notification {
  private int notificationId;
  private LocalDateTime createdOn;
  private String content;

  // TODO
  public boolean sendNotification(Account account) {
    return false;
  }
}
