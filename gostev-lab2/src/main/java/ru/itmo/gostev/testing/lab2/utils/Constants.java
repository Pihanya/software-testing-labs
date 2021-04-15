package ru.itmo.gostev.testing.lab2.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Constants {
  public static final Integer DEFAULT_MATH_CACHE;

  public static final BigDecimal DEFAULT_PRECISION;

  public static final Double DEFAULT_PRECISION_DOUBLE;

  public static final double PI_DOUBLE;

  public static final BigDecimal PI;

  public static final double MACHINE_EPSILON_DOUBLE;

  public static final BigDecimal MACHINE_EPSILON;

  private static final List<BigDecimal> FACTORIALS;

  static {
    { // Initialize core constants
      DEFAULT_MATH_CACHE = 1000;

      DEFAULT_PRECISION = BigDecimal.valueOf(0.0001D);
      DEFAULT_PRECISION_DOUBLE = 0.0001D;

      PI_DOUBLE = Math.PI;
      PI = BigDecimal.valueOf(PI_DOUBLE);
    }

    { // Initialize machine epsilon
      double eps = 1.0D;
      while(1.0D + 0.5 * eps != 1.0D) {
        eps /= 2;
      }
      MACHINE_EPSILON_DOUBLE = eps;
      MACHINE_EPSILON = BigDecimal.valueOf(eps);
    }

    { // Initialize first factorials
      FACTORIALS = new ArrayList<>(DEFAULT_MATH_CACHE);
      //noinspection ResultOfMethodCallIgnored
      FACTORIALS.add(BigDecimal.ONE); // 0!
      factorial(DEFAULT_MATH_CACHE - 1);
    }
  }

  public static BigDecimal factorial(final int x) {
    if(x < 0) {
      throw new IllegalArgumentException("Factorial cannot handle negative argument!");
    }

    if(x < FACTORIALS.size()) {
      return FACTORIALS.get(x);
    }

    final BigDecimal argument = BigDecimal.valueOf(x);
    final BigDecimal countedFactorial = argument.multiply(factorial(x - 1));

    FACTORIALS.add(countedFactorial);
    return countedFactorial;
  }
}
