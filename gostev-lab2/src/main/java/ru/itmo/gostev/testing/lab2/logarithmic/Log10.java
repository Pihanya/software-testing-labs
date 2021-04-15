package ru.itmo.gostev.testing.lab2.logarithmic;

import java.math.BigDecimal;

public class Log10 extends LogN {

  public Log10(final LogE logE) {
    super(logE, BigDecimal.valueOf(10));
  }
}
