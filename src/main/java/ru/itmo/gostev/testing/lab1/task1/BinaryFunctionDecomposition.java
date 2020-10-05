package ru.itmo.gostev.testing.lab1.task1;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class BinaryFunctionDecomposition {

  private final BigDecimal epsilon;
  private final BinaryFunction binaryFunction;

  public BinaryFunctionDecomposition(
      final BigDecimal epsilon,
      final BinaryFunction binaryFunction
  ) {
    this.epsilon = epsilon;
    this.binaryFunction = binaryFunction;
  }

  public BigDecimal decompose(final BigDecimal x) {
    final AtomicInteger counter = new AtomicInteger(0);

    return Stream.generate(counter::getAndIncrement)
        .map(n -> binaryFunction.evaluate(x, n))
        .takeWhile(value -> value.abs().compareTo(epsilon) >= 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
