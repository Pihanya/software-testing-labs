package ru.itmo.anokhin.testing.lab2.logarithmic;

import java.math.BigDecimal;

public class Log2 extends LogN {

  public Log2(final LogE logE) {
    super(logE, BigDecimal.valueOf(2));
  }
}
