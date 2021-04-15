package ru.itmo.anokhin.testing.lab2.task;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import ru.itmo.anokhin.testing.lab2.UnaryMathFunction;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

/**
 * (log5(x) * log5(x) + log3(x) - log2(x)) * log5(x) + log2(x) / ln(x)
 */
public class LogarithmicPart implements UnaryMathFunction {

  private final MathFacade mathFacade;

  public LogarithmicPart(final MathFacade mathFacade) {
    this.mathFacade = Objects.requireNonNull(mathFacade);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    final MathFacade m = mathFacade;

    // a: (log5(x) * log5(x) + log3(x) - log2(x)) * log5(x)
    final BigDecimal a = m.log5(x).multiply(m.log5(x))
        .add(m.log3(x))
        .subtract(m.log2(x));

    // b: log2(x) / ln(x)
    final BigDecimal b = m.log2(x).divide(m.ln(x), MathContext.DECIMAL128);

    // result: a + b
    final BigDecimal result = a.add(b);

    return result;
  }
}
