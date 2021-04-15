package ru.itmo.anokhin.testing.lab1.task1S.function;

import java.math.BigDecimal;
import java.util.stream.Stream;

public interface MathFunction {
  Number evaluate(MathFunction... arguments);

  default Number evaluate(final BigDecimal... arguments) {
    return evaluate(Stream.of(arguments).map(Number::new).toArray(MathFunction[]::new));
  }

  default Number evaluate(final Double... arguments) {
    return evaluate(Stream.of(arguments).map(BigDecimal::valueOf).toArray(BigDecimal[]::new));
  }

  MathFunction derive();
}
