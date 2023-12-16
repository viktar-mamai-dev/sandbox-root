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
public class ShipmentLog {
  private String shipmentNumber;
  private ShipmentStatus status;
  private LocalDateTime creationDate;
}
