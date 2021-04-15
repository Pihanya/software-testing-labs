package ru.itmo.anokhin.testing.lab2;

import java.math.BigDecimal;
import ru.itmo.anokhin.testing.lab2.utils.Constants;
import ru.itmo.anokhin.testing.lab2.utils.MathFacade;

public class JavaMathFacade extends MathFacade {

  public JavaMathFacade() {
    super(Constants.DEFAULT_PRECISION);
  }

  @Override
  public UnaryMathFunction ln() {
    return x -> BigDecimal.valueOf(Math.log(x.doubleValue()));
  }

  @Override
  public UnaryMathFunction log2() {
    return x -> BigDecimal.valueOf(logN(2, x.doubleValue()));
  }

  @Override
  public UnaryMathFunction log3() {
    return x -> BigDecimal.valueOf(logN(3, x.doubleValue()));
  }

  @Override
  public UnaryMathFunction log5() {
    return x -> BigDecimal.valueOf(logN(5, x.doubleValue()));
  }

  @Override
  public UnaryMathFunction sin() {
    return x -> BigDecimal.valueOf(Math.sin(x.doubleValue()));
  }

  @Override
  public UnaryMathFunction cos() {
    return x -> BigDecimal.valueOf(Math.cos(x.doubleValue()));
  }

  @Override
  public UnaryMathFunction tan() {
    return x -> BigDecimal.valueOf(Math.tan(x.doubleValue()));
  }

  @Override
  public UnaryMathFunction cosec() {
    return x -> BigDecimal.valueOf(1 / Math.sin(x.doubleValue()));
  }

  @Override
  public UnaryMathFunction sec() {
    return x -> BigDecimal.valueOf(1 / Math.cos(x.doubleValue()));
  }

  @Override
  public double logN(double n, double x) {
    return Math.log(x) / Math.log(n);
  }

  @Override
  public BigDecimal logN(BigDecimal n, BigDecimal x) {
    return BigDecimal.valueOf(logN(n.doubleValue(), x.doubleValue()));
  }
}
