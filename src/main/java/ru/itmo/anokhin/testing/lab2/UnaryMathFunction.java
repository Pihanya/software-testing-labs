package ru.itmo.anokhin.testing.lab2;

import java.math.BigDecimal;

public interface UnaryMathFunction {
  BigDecimal calculate(BigDecimal x);

  default double calculate(double x) {
    return calculate(BigDecimal.valueOf(x)).doubleValue();
  }
}
