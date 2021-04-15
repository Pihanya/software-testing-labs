package ru.itmo.anokhin.testing.lab2;

import java.math.BigDecimal;
import java.util.Objects;

public class AbstractMathFunction {

  protected static final Long MAX_COMPUTATION_ITERATIONS = 1_000_000L;

  private final BigDecimal precision;

  public AbstractMathFunction(BigDecimal precision) {
    Objects.requireNonNull(precision);
    this.precision = precision.abs();
  }

  public AbstractMathFunction(double precision) {
    this(BigDecimal.valueOf(precision));
  }

  public final BigDecimal getPrecision() {
    return precision;
  }

  protected boolean isDifferenceInsignificant(final BigDecimal firstValue, final BigDecimal secondValue) {
    return secondValue.subtract(firstValue).abs().compareTo(precision) < 0;
  }
}
