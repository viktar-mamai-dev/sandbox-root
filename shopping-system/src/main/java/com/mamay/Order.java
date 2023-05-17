package com.mamay;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {
  private String orderNumber;
  private OrderStatus status;
  private LocalDateTime orderDate;
  private List<OrderLog> orderLog;

  // TODO implement methods
  public boolean sendForShipment() {
    return false;
  }

  public boolean makePayment(Payment payment) {
    return false;
  }

  public boolean addOrderLog(OrderLog orderLog) {
    return this.orderLog.add(orderLog);
  }
}
