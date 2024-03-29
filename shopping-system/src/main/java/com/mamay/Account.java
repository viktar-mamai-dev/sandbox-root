package com.mamay;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
  private String userName;
  private String password;
  private AccountStatus status;
  private String name;
  private Address shippingAddress;
  private String email;
  private String phone;

  private List<CreditCard> creditCards;
  private List<ElectronicBankTransfer> bankAccounts;

  // TODO
  public boolean addProduct(Product product) {
    return false;
  }

  public boolean addProductReview(ProductReview review) {
    return false;
  }

  public boolean resetPassword() {
    return false;
  }
}
