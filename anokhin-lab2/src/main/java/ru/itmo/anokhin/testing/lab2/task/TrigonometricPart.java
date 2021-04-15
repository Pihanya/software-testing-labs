package ru.itmo.anokhin.testing.lab2.task;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import ru.itmo.anokhin.testing.lab2.UnaryMathFunction;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

/**
 * ((sec(x) * sin(x))^2 / sin(x) + cos(x)) * (sin(x)^2 - ((tan(x) / (tan(x) - csc(x))) * (sin(x) * tan(x) + csc(x))))
 */
public class TrigonometricPart implements UnaryMathFunction {

  private final MathFacade mathFacade;

  public TrigonometricPart(final MathFacade mathFacade) {
    this.mathFacade = Objects.requireNonNull(mathFacade);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    final MathFacade m = mathFacade;

    // a: (sec(x) * sin(x))^2 / sin(x) + cos(x)
    final BigDecimal a = m.sec(x).multiply(m.sin(x)).pow(2)
        .divide(m.sin(x), MathContext.DECIMAL128)
        .add(m.cos(x));

    // b: sin(x)^2
    final BigDecimal b = m.sin(x).pow(2);

    // c: tan(x) / (tan(x) - csc(x))
    final BigDecimal c = m.tan(x)
        .divide(m.tan(x).subtract(m.cosec(x)), MathContext.DECIMAL128);

    // d: sin(x) * tan(x) + csc(x)
    final BigDecimal d = m.sin(x).multiply(m.tan(x)).add(m.cosec(x));

    // result: a * (b - (c * d))
    final BigDecimal result = a.multiply(b.subtract(c.multiply(d)));

    return result;
  }
}
