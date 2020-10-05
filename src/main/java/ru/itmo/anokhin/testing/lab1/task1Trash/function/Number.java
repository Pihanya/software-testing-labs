package ru.itmo.anokhin.testing.lab1.task1S.function;

import java.math.BigDecimal;

public class Number implements MathFunction {

  private final BigDecimal value;

  public Number(final BigDecimal value) {
    this.value = value;
  }

  public BigDecimal getValue() {
    return value;
  }

  @Override
  public Number evaluate(MathFunction... arguments) {
    return this;
  }

  @Override
  public Zero derive() {
    return new Zero();
  }
}
