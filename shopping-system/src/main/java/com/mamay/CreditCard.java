package com.mamay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreditCard {
  private String nameOnCard;
  private String cardNumber;
  private int code;
  private Address billingAddress;
}
