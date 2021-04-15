package ru.itmo.anokhin.testing.lab2.trigonomertic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import ru.itmo.anokhin.testing.lab2.UnaryMathFunction;
import ru.itmo.anokhin.testing.lab2.utils.Constants;

public class Cos implements UnaryMathFunction {

  private final Sin sin;

  public Cos(final Sin sin) {
    this.sin = Objects.requireNonNull(sin);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    final BigDecimal sign;
    {
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
      sign = scaledX.compareTo(piD2) >= 0 ? BigDecimal.ONE : BigDecimal.ONE.negate();
    }

    final BigDecimal absoluteCos;
    {
      final BigDecimal sin = this.sin.calculate(x);
      absoluteCos = BigDecimal.ONE.subtract(sin.pow(2)).sqrt(MathContext.DECIMAL128);
    }

    return sign.multiply(absoluteCos);
  }
}
