package com.mamay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShipmentLog {
    private String shipmentNumber;
    private ShipmentStatus status;
    private LocalDateTime creationDate;
}
