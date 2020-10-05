package ru.itmo.anokhin.testing.lab1.task1S.function;

import java.math.BigDecimal;

public class ArgumentDefinition {
  private final String key;
  private final BigDecimal value;

  public ArgumentDefinition(final String key, final BigDecimal value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public BigDecimal getValue() {
    return value;
  }
}
