package ru.itmo.anokhin.testing.lab1.task1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SinusRecurrentDecompositionMemberGenerator implements UnaryFunctionDecompositionMemberGenerator {

  private final List<DecompositionMember> decompositionMembers = new ArrayList<>();

  public SinusRecurrentDecompositionMemberGenerator() {
  }

  @Override
  public DecompositionMember getDecompositionMember(final Integer memberIndex) {
    if (memberIndex < decompositionMembers.size()) {
      return decompositionMembers.get(memberIndex);
    }

    final DecompositionMember member;
    if (memberIndex == 0) {
      member = argument -> argument;
    } else {
      member = (argument) -> {
        final DecompositionMember previousMember = getDecompositionMember(memberIndex - 1);

        // sin(X) = x - x^3/3! + x^5/5! + ... + (-1)^N * x^(2N + 1)/(2N + 1)! + o(x^(2N + 2))
        // T(N) = T(N - 1) * signDiff * poweredArgumentDiff / denominatorDiff = T(N - 1) * (-1) * x^2 / ((2N + 1) * 2N)
        final BigDecimal signDiff = BigDecimal.ONE.negate(); // -1
        final BigDecimal poweredArgumentDiff = argument.pow(2); // x^2

        final BigDecimal denominatorDiff;
        {
          final BigDecimal doubledN = BigDecimal.valueOf(memberIndex).multiply(BigDecimal.valueOf(2)); // 2N
          denominatorDiff = doubledN.add(BigDecimal.ONE).multiply(doubledN); // (2N + 1) * 2N
        }

        return previousMember.evaluate(argument)
            .multiply(signDiff)
            .multiply(poweredArgumentDiff)
            .divide(denominatorDiff, RoundingMode.UP);
      };
    }

    decompositionMembers.add(member);
    return member;
  }

  @Override
  public DecompositionMember littleO(Integer memberIndex) {
    // o(x^(2N + 2)) = o(x^N) * o(x^N) * o(x^2) = x^(N + 1) * x^(N + 1) = x^(2N + 2)
    return (argument -> BigDecimal.ZERO);
  }
}
