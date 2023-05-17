/*
 * Copyright (c) 2023
 */
package com.mamay.lambdas.pack1;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

class DiscountRunner {

  private interface Discounter extends UnaryOperator<BigDecimal> {

    default Discounter combine(Discounter after) {
      return new Discounter() {
        @Override
        public BigDecimal apply(BigDecimal value) {
          return after.apply(this.apply(value));
        }
      };
    }
  }

  public static void main(String[] args) {
    BigDecimal a = BigDecimal.ONE;
  }
}
