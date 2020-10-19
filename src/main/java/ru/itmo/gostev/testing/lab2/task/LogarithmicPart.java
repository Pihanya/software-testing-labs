package ru.itmo.gostev.testing.lab2.task;

import java.math.BigDecimal;
import java.util.Objects;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.MathFacade;

/**
 * (((log3(x) - log3(x)) + (log2(x) * log2(x))) / log2(x) + log10(x)) - (log2(x) - log10(x) + log5(x))
 */
public class LogarithmicPart implements UnaryMathFunction {

  private final MathFacade mathFacade;

  public LogarithmicPart(final MathFacade mathFacade) {
    this.mathFacade = Objects.requireNonNull(mathFacade);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    final MathFacade m = mathFacade;

    // a: ((log3(x) - log3(x)) + (log2(x) * log2(x))) / log2(x)
    final BigDecimal a = m.log3(x).subtract(m.log3(x))
        .add(m.log2(x).multiply(m.log2(x)))
        .divide(m.log2(x));

    // b: log10(x)
    final BigDecimal b = m.log10(x);

    // c: log2(x) - log10(x) + log5(x)
    final BigDecimal c = m.log2(x).subtract(m.log10(x)).add(m.log5(x));

    // result: a + b - c
    final BigDecimal result = a.add(b).subtract(c);

    return result;
  }
}
