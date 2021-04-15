package ru.itmo.gostev.testing.lab1.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ArcSinusDecompositionTest {

  private static final Double EPSILON;

  static {
    double machEps = 1.0;
    do {
      machEps /= 2.0;
    } while ((1.0 + (machEps / 2.0)) != 1.0);

    EPSILON = machEps;
  }

  @Test
  public void testPlainData() {
    Map.of(
        /*1D, Math.PI / 2,*/
        0.5D, Math.PI / 6,
        1D / Math.sqrt(2), Math.PI / 4,
        Math.sqrt(3) / 2, Math.PI / 3
        /*-1D, -Math.PI / 2*/
    ).forEach(this::testArcSin);
  }

  @Test
  public void positiveTrigonometricalCircleComparingWithJavaMath() {
    final double step = Math.PI / 64;
    for (double current = -2 * Math.PI; current < 2 * Math.PI; current += step) {
      testArcSinNeighborhood(current, step / 64);
    }
  }

  @Test
  public void positiveTestRemarkablePointsComparingWithJavaMath() {
    final double step = Math.PI / 16;
    for (double current = -2 * Math.PI; current < 2 * Math.PI; current += step) {
      testArcSinNeighborhood(current, EPSILON * 50);
    }
  }

  private void testArcSinNeighborhood(double point, double radius) {
    final int neighborhoodRadiusSteps = 50;
    final AtomicInteger counter = new AtomicInteger(-neighborhoodRadiusSteps);

    Stream.generate(() -> point + counter.getAndIncrement() * radius / neighborhoodRadiusSteps)
        .takeWhile(argument -> counter.get() <= neighborhoodRadiusSteps)
        .forEach(argument -> testArcSin(argument, Math.asin(argument)));
  }

  private void testArcSin(final Double x, final Double expected) {
    final Double actual = arcsin(x, EPSILON);
    if (Math.abs(actual - expected) > EPSILON) {
      assertEquals(expected, actual);
    }
  }

  private static Function<Double, Double> arcsin(final Double epsilon) {
    return x -> new BinaryFunctionDecomposition(new BigDecimal(epsilon, MathContext.DECIMAL128),
        new ArcSinusRecurrent(new FactorialRecurrent()))
        .decompose(new BigDecimal(x, MathContext.DECIMAL128))
        .doubleValue();
  }

  private static Double arcsin(final Double x, final Double epsilon) {
    return arcsin(epsilon).apply(x);
  }
}
