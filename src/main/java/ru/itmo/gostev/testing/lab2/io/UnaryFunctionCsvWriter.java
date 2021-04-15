package ru.itmo.gostev.testing.lab2.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.csv.CSVPrinter;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;
import ru.itmo.gostev.testing.lab2.utils.IOConstants;

public class UnaryFunctionCsvWriter {

  private final File outputFile;
  private final UnaryMathFunction unaryFunction;

  public UnaryFunctionCsvWriter(final File outputFile, final UnaryMathFunction unaryFunction) {
    this.outputFile = outputFile;
    this.unaryFunction = unaryFunction;
  }

  public void writeSeries(double startX, double endX, double xStep) {
    if (startX > endX || Math.abs(endX - startX) < Constants.MACHINE_EPSILON_DOUBLE) {
      throw new IllegalArgumentException("Left X border must be less than right X border");
    }
    if (Math.abs(xStep) < Constants.MACHINE_EPSILON_DOUBLE) {
      throw new IllegalArgumentException("X step must be other than zero");
    }

    try {
      final FileWriter fileWriter = new FileWriter(outputFile);
      try (final CSVPrinter printer = new CSVPrinter(fileWriter, IOConstants.UNARY_FUNCTION_WRITER_CSV_FORMAT)) {
        for (int counter = 0; ; ++counter) {
          double x = startX + xStep * counter;
          if (x > endX) {
            break;
          }
          if (Math.abs(endX - x) <= Constants.MACHINE_EPSILON_DOUBLE) {
            x = endX;
          }

          final double y = unaryFunction.calculate(x);
          printer.printRecord(x, y);
        }
      }
    } catch (final IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
