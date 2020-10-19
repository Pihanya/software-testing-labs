package ru.itmo.anokhin.testing.lab2.io;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.itmo.anokhin.testing.lab2.TestUtils.createTempFile;
import static ru.itmo.anokhin.testing.lab2.TestUtils.valuesFitAllowedDifference;
import static ru.itmo.anokhin.testing.lab2.utils.Constants.DEFAULT_PRECISION;
import static ru.itmo.anokhin.testing.lab2.utils.Constants.DEFAULT_PRECISION_DOUBLE;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.itmo.anokhin.testing.lab2.UnaryMathFunction;
import ru.itmo.anokhin.testing.lab2.utils.Constants;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

@TestInstance(Lifecycle.PER_CLASS)
public class IOTest {

  private static final File FILE = createTempFile("io_test", "csv");
//  private static final File FILE = new File("io_test.csv");

  private static final MathFacade FACADE = new MathFacade(DEFAULT_PRECISION);

  private static final UnaryMathFunction FUNCTION = FACADE.sin();
  final Double LEFT_X = (-2) * Constants.PI_DOUBLE;
  final Double RIGHT_X = 2 * Constants.PI_DOUBLE;
  final Double X_STEP = 0.001D;

  @AfterAll
  public static void breakDown() {
    if (FILE.exists()) {
      FILE.delete();
    }
  }

  @Test
  @Order(1)
  public void testWrite() {
    final UnaryFunctionCsvWriter writer = new UnaryFunctionCsvWriter(FILE, FUNCTION);
    writer.writeSeries(LEFT_X, RIGHT_X, X_STEP);
  }

  @Test
  @Order(2)
  public void testRead() {
    final CsvFileUnaryFunction fileFunction = new CsvFileUnaryFunction(FILE, DEFAULT_PRECISION);

    final List<BigDecimal> xValues = new ArrayList<>();
    {
      final BigDecimal leftX = BigDecimal.valueOf(LEFT_X);
      final BigDecimal rightX = BigDecimal.valueOf(RIGHT_X);
      final BigDecimal xStep = BigDecimal.valueOf(X_STEP);

      for (BigDecimal x = leftX; x.compareTo(rightX) <= 0; x = x.add(xStep)) {
        xValues.add(x);
      }
    }

    for (final BigDecimal x : xValues) {
      final double doubleX = x.doubleValue();
      final double y = fileFunction.calculate(doubleX);
      final double expectedY = FUNCTION.calculate(doubleX);

      assertTrue(
          valuesFitAllowedDifference(expectedY, y, DEFAULT_PRECISION_DOUBLE),
          () -> String.format(
              "Difference between COS(X) and FILE(X) at X = %f is bigger than precision (abs(%f - %f) > %f)",
              doubleX, expectedY, y, DEFAULT_PRECISION_DOUBLE
          )
      );
    }
  }
}
