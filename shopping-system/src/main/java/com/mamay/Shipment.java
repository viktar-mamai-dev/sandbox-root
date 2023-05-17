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
public class Shipment {
  private String shipmentNumber;
  private LocalDateTime shipmentDate;
  private LocalDateTime estimatedArrival;
  private String shipmentMethod;
  private List<ShipmentLog> shipmentLogs;

  public boolean addShipmentLog(ShipmentLog shipmentLog) {
    this.shipmentLogs.add(shipmentLog);
    return true;
  }
}
