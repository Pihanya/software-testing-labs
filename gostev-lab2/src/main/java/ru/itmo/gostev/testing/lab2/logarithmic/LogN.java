package ru.itmo.gostev.testing.lab2.logarithmic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;

public class LogN implements UnaryMathFunction {

  private final LogE logE;
  private final BigDecimal base;

  public LogN(final LogE logE, final BigDecimal base) {
    this.logE = Objects.requireNonNull(logE);

    Objects.requireNonNull(base);
    if(base.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Logarithm base must be greater than zero");
    }
    this.base = base;
  }

  @Override
  public BigDecimal calculate(BigDecimal x) {
    if(x.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("LogN argument must be bigger than zero");
    }

    if(x.subtract(BigDecimal.ONE).abs().compareTo(Constants.MACHINE_EPSILON) <= 0) {
      return BigDecimal.ZERO;
    }

    final BigDecimal numerator = logE.calculate(x);
    final BigDecimal denominator = logE.calculate(base);

    return numerator.divide(denominator, MathContext.DECIMAL128);
  }
}
