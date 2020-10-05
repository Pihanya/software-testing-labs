package ru.itmo.anokhin.testing.lab1.task1S.operator;

import ru.itmo.anokhin.testing.lab1.task1S.function.MathFunction;

public interface BinaryOperator {
  MathFunction apply(MathFunction first, MathFunction second);
}
