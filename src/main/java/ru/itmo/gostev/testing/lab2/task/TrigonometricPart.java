package ru.itmo.gostev.testing.lab2.task;

import java.math.BigDecimal;
import java.util.Objects;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.MathFacade;

/**
 * (csc(x)^2 + sec(x)) + (cot(x) - sin(x)) - cos(x)) - cot(x)
 */
public class TrigonometricPart implements UnaryMathFunction {

  private final MathFacade mathFacade;

  public TrigonometricPart(final MathFacade mathFacade) {
    this.mathFacade = Objects.requireNonNull(mathFacade);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    final MathFacade m = mathFacade;

    // a: csc(x)^2 + sec(x)
    final BigDecimal a = m.cosec(x).pow(2).add(m.sec(x));

    // b: cot(x) - sin(x) - cos(x)
    final BigDecimal b = m.cot(x).subtract(m.sin(x)).subtract(m.cos(x));

    // c: cot(x)
    final BigDecimal c = m.cot(x);

    // result: a + b - c
    final BigDecimal result = a.add(b).subtract(c);

    return result;
  }
}
