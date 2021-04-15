package ru.itmo.gostev.testing.lab2;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Map;
import ru.itmo.gostev.testing.lab2.io.CsvFileUnaryFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;


@SuppressWarnings("unused")
public class TestUtils {

  public static void testUnaryFunction(final UnaryMathFunction function, final File tableFile, final BigDecimal precision) {
    final CsvFileUnaryFunction fileFunction = new CsvFileUnaryFunction(tableFile);

    final List<BigDecimal> xValues = fileFunction.getXValues();
    for (final BigDecimal x : xValues) {
      final BigDecimal y = function.calculate(x);
      final BigDecimal expectedY = fileFunction.calculate(x);

      valuesFitAllowedDifference(expectedY, y, precision);
    }
  }

  public static void testUnaryFunction(
      final UnaryMathFunction testedFunction, final UnaryMathFunction actualValuesFunction,
      final List<Double> xValues, final BigDecimal precision
  ) {
    xValues.forEach(x -> valuesFitAllowedDifference(testedFunction.calculate(x), actualValuesFunction.calculate(x), precision.doubleValue()));
  }

  public static <T extends UnaryMathFunction> T stubWith(final T mock, final File stubValuesFile) {
    final CsvFileUnaryFunction stub = new CsvFileUnaryFunction(stubValuesFile);

    when(mock.calculate(any(BigDecimal.class))).thenAnswer(invocation -> {
      final BigDecimal x = invocation.getArgumentAt(0, BigDecimal.class);
      return stub.calculate(x);
    });

    return mock;
  }

  public static <T extends UnaryMathFunction> T stubWith(final T mock, final UnaryMathFunction with) {
    when(mock.calculate(any(BigDecimal.class))).thenAnswer(invocation -> {
      final BigDecimal x = invocation.getArgumentAt(0, BigDecimal.class);
      return with.calculate(x);
    });

    return mock;
  }

  public static <T extends UnaryMathFunction> T stubWith(final T mock, final Map<Double, Double> values) {
    when(mock.calculate(any(BigDecimal.class))).thenAnswer(invocation -> {
      final BigDecimal x = invocation.getArgumentAt(0, BigDecimal.class);

      BigDecimal leastDifference = null;
      BigDecimal leastDifferenceY = null;
      for (Map.Entry<Double, Double> entry : values.entrySet()) {
        final BigDecimal valueX = BigDecimal.valueOf(entry.getKey());
        final BigDecimal valueY = BigDecimal.valueOf(entry.getValue());

        final BigDecimal difference = valueX.subtract(x).abs();
        if (leastDifference == null || difference.compareTo(leastDifference) < 0) {
          leastDifference = difference;
          leastDifferenceY = valueY;
        }
      }

      assert leastDifference != null;
      if (leastDifference.compareTo(Constants.MACHINE_EPSILON) > 0) {
        throw new IllegalStateException(String.format("Could not find value of function for X = %f in file", x));
      }

      return leastDifferenceY;
    });

    return mock;
  }

  public static boolean valuesFitAllowedDifference(final double firstValue, final double secondValue, final double allowedDifference) {
    return valuesFitAllowedDifference(BigDecimal.valueOf(firstValue), BigDecimal.valueOf(secondValue),
        BigDecimal.valueOf(allowedDifference));
  }

  public static boolean valuesFitAllowedDifference(final BigDecimal firstValue, final BigDecimal secondValue,
      final BigDecimal allowedDifference) {
    final BigDecimal difference = secondValue.subtract(firstValue).abs();
    return difference.compareTo(allowedDifference) <= 0
        || difference.subtract(allowedDifference).abs().compareTo(Constants.MACHINE_EPSILON) <= 0;
  }

  public static File asResourceFile(final String file) {
    final String resourceFilePath = TestUtils.class.getClassLoader().getResource(file).getFile();
    return new File(resourceFilePath);
  }

  public static File createTempFile(final String filename, final String extension) {
    try {
      final Path filePath = Files.createTempFile(
          filename, '.' + extension,
          PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxrwxrwx"))
      );
      return filePath.toFile();
    } catch (final IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
