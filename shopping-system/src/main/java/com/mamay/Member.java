package com.mamay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member extends Customer {
    private Account account;

    // TODO
    public OrderStatus placeOrder(Order order) {
        return null;
    }
}
