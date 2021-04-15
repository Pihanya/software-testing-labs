package ru.itmo.gostev.testing.lab2.trigonomertic;

import java.math.BigDecimal;
import java.math.MathContext;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;

public class Cot implements UnaryMathFunction {

  private final Sin sin;
  private final Cos cos;

  public Cot(final Sin sin, final Cos cos) {
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

    if(scaledX.abs().compareTo(Constants.MACHINE_EPSILON) <= 0 || scaledX.subtract(Constants.PI).abs().compareTo(Constants.MACHINE_EPSILON) <= 0) {
      throw new IllegalArgumentException("Argument of cot function must not be PI * N");
    }
  }
}
