package ru.itmo.gostev.testing.lab2.task;

import java.math.BigDecimal;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;
import ru.itmo.gostev.testing.lab2.utils.MathFacade;

/**
 * Equations system:
 * (csc(x)^2 + sec(x)) + (cot(x) - sin(x)) - cos(x)) - cot(x), if x <= 0
 * (((log3(x) - log3(x)) + (log2(x) * log2(x))) / log2(x) + log10(x)) - (log2(x) - log10(x) + log5(x)), if x > 0
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
