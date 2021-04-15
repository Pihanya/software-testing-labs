package ru.itmo.gostev.testing.lab2.trigonomertic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import ru.itmo.gostev.testing.lab2.AbstractMathFunction;
import ru.itmo.gostev.testing.lab2.UnaryMathFunction;
import ru.itmo.gostev.testing.lab2.utils.Constants;

public class Sin extends AbstractMathFunction implements UnaryMathFunction {

  private static final Map<Integer, BigDecimal> TAYLOR_COEFFICIENTS = new HashMap<>(Constants.DEFAULT_MATH_CACHE);

  public Sin(final BigDecimal precision) {
    super(precision);
  }

  @Override
  public BigDecimal calculate(final BigDecimal x) {
    // sin(x) = x/1! - x^3/3! + x^5/5! - ... + (-1)^(n + 1) * x^(2n - 1)/((2n-1)!)
    BigDecimal result = BigDecimal.ZERO;
    for (int n = 1; ; ++n) {
      final BigDecimal currentMember = countTaylorMember(n, x);
      final BigDecimal newResult = result.add(currentMember);

      if (isDifferenceInsignificant(result, newResult)) {
        break;
      }

      result = newResult;
    }

    if(result.compareTo(BigDecimal.ONE) > 0) {
      return BigDecimal.ONE;
    } else if(result.negate().compareTo(BigDecimal.ONE) > 0) {
      return BigDecimal.ONE.negate();
    } else {
      return result;
    }
  }

  private static BigDecimal countTaylorMember(final int n, final BigDecimal x) {
    return x.pow(2 * n - 1).multiply(countTaylorCoefficient(n));
  }

  private static BigDecimal countTaylorCoefficient(final int n) {
    return TAYLOR_COEFFICIENTS.computeIfAbsent(n, (N) -> {
      final BigDecimal sign = ((N + 1) % 2) == 0 ? BigDecimal.ONE : BigDecimal.ONE.negate();

      final BigDecimal nominator = BigDecimal.ONE;
      final BigDecimal denominator = Constants.factorial(2 * N - 1);

      return sign.multiply(nominator).divide(denominator, MathContext.DECIMAL128);
    });
  }
}
