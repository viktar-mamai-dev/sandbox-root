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
public class OrderLog {
  private String orderNumber;
  private LocalDateTime creationDate;
  private OrderStatus status;
}
