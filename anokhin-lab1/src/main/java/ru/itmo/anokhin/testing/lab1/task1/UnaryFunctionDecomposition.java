package ru.itmo.anokhin.testing.lab1.task1;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class UnaryFunctionDecomposition {

  private final BigDecimal epsilon;
  private final UnaryFunctionDecompositionMemberGenerator memberGenerator;

  public UnaryFunctionDecomposition(
      final BigDecimal epsilon,
      final UnaryFunctionDecompositionMemberGenerator memberGenerator
  ) {
    this.epsilon = epsilon;
    this.memberGenerator = memberGenerator;
  }

  public BigDecimal decompose(final BigDecimal x) {
    final AtomicInteger counter = new AtomicInteger(0);

    return Stream.generate(counter::getAndIncrement)
        .map(memberGenerator::getDecompositionMember)
        .map(decompositionMember -> decompositionMember.evaluate(x))
        .takeWhile(decompositionMemberValue -> decompositionMemberValue.abs().compareTo(epsilon) >= 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .add(memberGenerator.littleO(counter.get()).evaluate(x));
  }
}
