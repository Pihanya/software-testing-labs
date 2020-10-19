package ru.itmo.anokhin.testing.lab2.task;

import static ru.itmo.anokhin.testing.lab2.TestUtils.testUnaryFunction;
import static ru.itmo.anokhin.testing.lab2.utils.Constants.DEFAULT_PRECISION;
import static ru.itmo.anokhin.testing.lab2.utils.Constants.PI_DOUBLE;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.itmo.anokhin.testing.lab2.JavaMathFacade;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

public class TaskFunctionsTest {

  private static final BigDecimal PRECISION = DEFAULT_PRECISION;

  private static final MathFacade FACADE = new MathFacade(PRECISION);

  private static final MathFacade JAVA_FACADE = new JavaMathFacade();

  @Test
  public void testLogarithmicPart() {
    final LogarithmicPart testedFunction = new LogarithmicPart(FACADE);
    final LogarithmicPart trueValuesFunction = new LogarithmicPart(JAVA_FACADE);
    final List<Double> xValues = List.of(0.25, 0.5, 0.75, 2., 4., 256.);

    testUnaryFunction(testedFunction, trueValuesFunction, xValues, DEFAULT_PRECISION);
  }

  @Test
  public void testTrigonometricPart() {
    final TrigonometricPart testedFunction = new TrigonometricPart(FACADE);
    final TrigonometricPart trueValuesFunction = new TrigonometricPart(JAVA_FACADE);
    final List<Double> xValues = List.of(
        -PI_DOUBLE - PI_DOUBLE / 16, -PI_DOUBLE - PI_DOUBLE / 8, -PI_DOUBLE - PI_DOUBLE / 4,
        -PI_DOUBLE / 16, -PI_DOUBLE / 8, -PI_DOUBLE / 4
    );

    testUnaryFunction(testedFunction, trueValuesFunction, xValues, DEFAULT_PRECISION);
  }

  @Test
  public void integrationTest() {
    final TaskEquationSystem testedFunction = new TaskEquationSystem(FACADE);
    final TaskEquationSystem trueValuesFunction = new TaskEquationSystem(JAVA_FACADE);
    final List<Double> xValues = List.of(
        -PI_DOUBLE - PI_DOUBLE / 16, -PI_DOUBLE - PI_DOUBLE / 8, -PI_DOUBLE - PI_DOUBLE / 4,
        -PI_DOUBLE / 16, -PI_DOUBLE / 8, -PI_DOUBLE / 4,
        0.25, 0.5, 0.75, 2., 4., 256.
    );

    testUnaryFunction(testedFunction, trueValuesFunction, xValues, DEFAULT_PRECISION);
  }
}
