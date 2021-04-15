package ru.itmo.anokhin.testing.lab2.trigonomertic;

import java.math.BigDecimal;
import java.math.MathContext;
import ru.itmo.anokhin.testing.lab2.UnaryMathFunction;
import ru.itmo.anokhin.testing.lab2.utils.Constants;

public class Tan implements UnaryMathFunction {

  private final Sin sin;
  private final Cos cos;

  public Tan(final Sin sin, final Cos cos) {
    this.sin = sin;
    this.cos = cos;
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    checkArgument(x);

    final BigDecimal sin = this.sin.calculate(x);
    final BigDecimal cos = this.cos.calculate(x);

    return sin.divide(cos, MathContext.DECIMAL128);
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
      throw new IllegalArgumentException("Argument of tangent function must not be PI/2 * (2N + 1)");
    }
  }
}
