package ru.itmo.anokhin.testing.lab2.task;

import java.math.BigDecimal;
import ru.itmo.anokhin.testing.lab2.UnaryMathFunction;
import ru.itmo.anokhin.testing.lab2.utils.Constants;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

/**
 * Equations system:
 * ((sec(x) * sin(x))^2 / sin(x) + cos(x)) * (sin(x)^2 - ((tan(x) / (tan(x) - csc(x))) * (sin(x) * tan(x) + csc(x)))), if x <= 0
 * (log5(x) * log5(x) + log3(x) - log2(x)) * log5(x) + log2(x) / ln(x), if x > 0
 */
public class TaskEquationSystem implements UnaryMathFunction {

  private final TrigonometricPart trigonometricPart;

  private final LogarithmicPart logarithmicPart;

  public TaskEquationSystem(final MathFacade mathFacade) {
    this.trigonometricPart = new TrigonometricPart(mathFacade);
    this.logarithmicPart = new LogarithmicPart(mathFacade);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    if(x.compareTo(BigDecimal.ZERO) <= 0) { // x <= 0
      return trigonometricPart.calculate(x);
    } else { // x > 0
      return logarithmicPart.calculate(x);
    }
  }

  @Override
  public double calculate(final double x) {
    if(x < 0 || Math.abs(x) <= Constants.MACHINE_EPSILON_DOUBLE) { // x <= 0
      return trigonometricPart.calculate(x);
    } else { // x > 0
      return logarithmicPart.calculate(x);
    }
  }
}
