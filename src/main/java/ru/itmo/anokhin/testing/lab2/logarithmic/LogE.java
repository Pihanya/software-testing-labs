package ru.itmo.anokhin.testing.lab2.logarithmic;

import static ru.itmo.anokhin.testing.lab2.utils.Constants.DEFAULT_MATH_CACHE;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import ru.itmo.anokhin.testing.lab2.AbstractMathFunction;
import ru.itmo.anokhin.testing.lab2.UnaryMathFunction;
import ru.itmo.anokhin.testing.lab2.utils.Constants;

public class LogE extends AbstractMathFunction implements UnaryMathFunction {

  private static final Map<Integer, BigDecimal> TAYLOR_COEFFICIENTS = new HashMap<>(DEFAULT_MATH_CACHE);

  public LogE(final BigDecimal precision) {
    super(precision);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    if(x.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("LogN argument must be bigger than zero");
    }

    if(x.subtract(BigDecimal.ONE).abs().compareTo(Constants.MACHINE_EPSILON) <= 0) {
      return BigDecimal.ZERO;
    }

    // ln(1 + x) = x/1! - x^2/2! + x^3/3! + ... + (-1)^(n + 1) * x^n / n!
    final BigDecimal taylorX = x.subtract(BigDecimal.ONE);

    BigDecimal result = BigDecimal.ZERO;
    for(int n = 1;;++n) {
      final BigDecimal currentMember = countTaylorMember(n, taylorX);
      final BigDecimal newResult = result.add(currentMember);

      if(isDifferenceInsignificant(result, newResult)) {
        break;
      }

      result = newResult;
    }

    return result;
  }

  private static BigDecimal countTaylorMember(final int n, final BigDecimal x) {
    return x.multiply(countTaylorCoefficient(n));
  }

  private static BigDecimal countTaylorCoefficient(final int n) {
    return TAYLOR_COEFFICIENTS.computeIfAbsent(n, (N) -> {
      final BigDecimal sign = (N % 2) == 1 ? BigDecimal.ONE : BigDecimal.ONE.negate();

      final BigDecimal nominator = BigDecimal.ONE;
      final BigDecimal denominator = Constants.factorial(N);

      return sign.multiply(nominator).divide(denominator, MathContext.DECIMAL128);
    });
  }
}
