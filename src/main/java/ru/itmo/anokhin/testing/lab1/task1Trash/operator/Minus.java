package ru.itmo.anokhin.testing.lab1.task1S.operator;

import ru.itmo.anokhin.testing.lab1.task1S.function.MathFunction;
import ru.itmo.anokhin.testing.lab1.task1S.function.Number;
import ru.itmo.anokhin.testing.lab1.task1S.function.Zero;

public class Minus implements BinaryOperator, UnaryOperator {

  @Override
  public MathFunction apply(MathFunction first, MathFunction second) {
    return new MathFunction() {
      @Override
      public Number evaluate(MathFunction... arguments) {
        return new Number(first.evaluate(arguments).getValue().subtract(second.evaluate(arguments).getValue()));
      }

      @Override
      public MathFunction derive() {
        return apply(first.derive(), second.derive());
      }
    };
  }

  @Override
  public MathFunction apply(MathFunction function) {
    return apply(function, new Zero());
  }
}
