package ru.itmo.gostev.testing.lab1.task1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FactorialRecurrent implements UnaryFunction {

  private final List<BigDecimal> decompositionMembers = new ArrayList<>();

  @Override
  public BigDecimal evaluate(final Integer n) {
    if (n < decompositionMembers.size()) {
      return decompositionMembers.get(n);
    }

    final BigDecimal member;
    if (n == 0 || n == 1 || n == 2) {
      member = BigDecimal.valueOf(n == 0 ? 1 : n);
    } else {
      member = BigDecimal.valueOf(n).multiply(evaluate(n - 1));
    }

    decompositionMembers.add(member);
    return member;
  }
}
