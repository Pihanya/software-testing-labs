package ru.itmo.gostev.testing.lab2.trigonomertic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;

public class Sec implements UnaryMathFunction {

  private final Cos cos;

  public Sec(final Cos cos) {
    this.cos = Objects.requireNonNull(cos);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    checkArgument(x);

    final BigDecimal cos = this.cos.calculate(x);
    return BigDecimal.ONE.divide(cos, MathContext.DECIMAL128);
  }

  private static void checkArgument(final BigDecimal x) {
    BigDecimal scaledX;
    { // scale X from 0 to PI
      final BigDecimal leftBorder = BigDecimal.ZERO;
      final BigDecimal rightBorder = Constants.PI;

      scaledX = x;
      while(scaledX.compareTo(leftBorder) < 0) {
        scaledX = scaledX.add(Constants.PI);
      }
      while(scaledX.compareTo(rightBorder) > 0) {
        scaledX = scaledX.subtract(Constants.PI);
      }
    }

    final BigDecimal piD2 = Constants.PI.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
    if(scaledX.subtract(piD2).abs().compareTo(Constants.MACHINE_EPSILON) <= 0) {
      throw new IllegalArgumentException("Argument of sec function must not be PI/2 * (N + 1)");
    }
  }
}
