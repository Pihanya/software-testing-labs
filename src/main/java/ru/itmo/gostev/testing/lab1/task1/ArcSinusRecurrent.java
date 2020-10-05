package ru.itmo.gostev.testing.lab1.task1;

import java.math.BigDecimal;
import java.math.MathContext;

public class ArcSinusRecurrent implements BinaryFunction {

  private final FactorialRecurrent factorial;

  public ArcSinusRecurrent(
      FactorialRecurrent factorial
  ) {
    this.factorial = factorial;
  }

  @Override
  public BigDecimal evaluate(final BigDecimal x, final Integer n) {
    if (n == 0) {
      return x;
    } else {
      // sin(X) = x + x^3/6 + 3x^2/40 + ... + (2n - 1)!!x^(2N + 1)/(2N)!!(2N + 1)
      final BigDecimal nominator = factorial.evaluate(2 * n);
      final BigDecimal denominator = BigDecimal.valueOf(4).pow(n)
          .multiply(factorial.evaluate(n).pow(2))
          .multiply(BigDecimal.ONE.add(BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(n))))
          .multiply(x.pow(2 * n + 1));

      return nominator.divide(denominator, MathContext.DECIMAL128);
    }
  }
}
