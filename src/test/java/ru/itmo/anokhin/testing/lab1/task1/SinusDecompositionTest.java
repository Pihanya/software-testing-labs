package ru.itmo.anokhin.testing.lab1.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class SinusDecompositionTest {

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
    final BigDecimal two = BigDecimal.valueOf(2);
    final BigDecimal sqrt2 = two.sqrt(MathContext.DECIMAL128);// sqrt(2)
    final BigDecimal sqrt3 = BigDecimal.valueOf(3).sqrt(MathContext.DECIMAL128);// sqrt(3)

    Map.of(
        Math.PI / 2, 1.0D,
        Math.PI / 4, sqrt2.divide(two, MathContext.DECIMAL128).doubleValue(),
        Math.PI / 6, 0.5D,
        Math.PI / 3, sqrt3.divide(two, MathContext.DECIMAL128).doubleValue()
    ).forEach(this::testSin);
  }

  @Test
  public void positiveTrigonometricalCircleComparingWithJavaMath() {
    final double step = Math.PI / 64;
    for (double current = -2 * Math.PI; current < 2 * Math.PI; current += step) {
      testSinNeighborhood(current, step / 64);
    }
  }

  @Test
  public void positiveTestRemarkablePointsComparingWithJavaMath() {
    final double step = Math.PI / 16;
    for (double current = -2 * Math.PI; current < 2 * Math.PI; current += step) {
      testSinNeighborhood(current, EPSILON * 50);
    }
  }

  private void testSinNeighborhood(double point, double radius) {
    final int neighborhoodRadiusSteps = 50;
    final AtomicInteger counter = new AtomicInteger(-neighborhoodRadiusSteps);

    Stream.generate(() -> point + counter.getAndIncrement() * radius / neighborhoodRadiusSteps)
        .takeWhile(argument -> counter.get() <= neighborhoodRadiusSteps)
        .forEach(argument -> testSin(argument, Math.sin(argument)));
  }

  private void testSin(final Double x, final Double expected) {
    final Double actual = sin(x, EPSILON);
    if (Math.abs(actual - expected) > EPSILON) {
      assertEquals(expected, actual);
    }
  }

  private static Function<Double, Double> sin(final Double epsilon) {
    return x -> new UnaryFunctionDecomposition(new BigDecimal(epsilon, MathContext.DECIMAL128),
        new SinusRecurrentDecompositionMemberGenerator())
        .decompose(new BigDecimal(x, MathContext.DECIMAL128))
        .doubleValue();
  }

  private static Double sin(final Double x, final Double epsilon) {
    return sin(epsilon).apply(x);
  }
}
