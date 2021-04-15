package ru.itmo.anokhin.testing.lab1.task1;

public interface UnaryFunctionDecompositionMemberGenerator {
  DecompositionMember getDecompositionMember(Integer memberOrder);

  DecompositionMember littleO(Integer memberOrder);
}
