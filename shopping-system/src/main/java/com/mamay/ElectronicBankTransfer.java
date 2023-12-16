package com.mamay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ElectronicBankTransfer {
  private String bankName;
  private String routingNumber;
  private String accountNumber;
}
