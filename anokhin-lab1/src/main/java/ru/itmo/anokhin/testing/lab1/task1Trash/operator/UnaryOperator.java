package ru.itmo.anokhin.testing.lab1.task1S.operator;

import ru.itmo.anokhin.testing.lab1.task1S.function.MathFunction;

public interface UnaryOperator {
  MathFunction apply(final MathFunction function);
}
