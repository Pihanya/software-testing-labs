package ru.itmo.gostev.testing.lab2.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;
import ru.itmo.gostev.testing.lab2.utils.IOConstants;

public class CsvFileUnaryFunction implements UnaryMathFunction {
  private final File inputFile;

  private final BigDecimal maxDifference;

  private final Map<BigDecimal, BigDecimal> functionValues = new LinkedHashMap<>(Constants.DEFAULT_MATH_CACHE);

  public CsvFileUnaryFunction(final File inputFile, final BigDecimal maxDifference) {
    this.inputFile = Objects.requireNonNull(inputFile);
    if(!inputFile.exists()) {
      throw new IllegalArgumentException(String.format("File %s does not exist", inputFile.getAbsoluteFile()));
    }

    this.maxDifference = Objects.requireNonNull(maxDifference);
    readSeries();
  }

  public CsvFileUnaryFunction(final File inputFile) {
    this(inputFile, Constants.MACHINE_EPSILON);
  }

  public List<BigDecimal> getXValues() {
    return List.copyOf(functionValues.keySet());
  }

  public Map<BigDecimal, BigDecimal> getFunctionValues() {
    return Collections.unmodifiableMap(functionValues);
  }

  @Override
  public BigDecimal calculate(BigDecimal x) {
    BigDecimal leastDifference = null;
    BigDecimal leastDifferenceY = null;
    for(Map.Entry<BigDecimal, BigDecimal> entry : functionValues.entrySet()) {
      final BigDecimal recordX = entry.getKey();
      final BigDecimal recordY = entry.getValue();

      final BigDecimal difference = recordX.subtract(x).abs();
      if(leastDifference == null || difference.compareTo(leastDifference) < 0) {
        leastDifference = difference;
        leastDifferenceY = recordY;
      }
    }

    assert leastDifference != null;
    if(leastDifference.compareTo(maxDifference) > 0) {
      throw new IllegalStateException(String.format("Could not find value of function for X = %f in file", x));
    }

    return leastDifferenceY;
  }

  private void readSeries() {
    try {
      final Reader fileReader = new FileReader(inputFile);
      IOConstants.UNARY_FUNCTION_READER_CSV_FORMAT
          .parse(fileReader)
          .forEach(record -> functionValues.put(
              new BigDecimal(record.get(IOConstants.UNARY_FUNCTION_X_COLUMN)),
              new BigDecimal(record.get(IOConstants.UNARY_FUNCTION_Y_COLUMN))
          ));

      if(functionValues.size() == 0) {
        throw new IllegalArgumentException("File does not contain any values");
      }
    } catch (final IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
