package ru.itmo.anokhin.testing.lab1.task1S.operator;

import static ru.itmo.anokhin.testing.lab1.task1S.util.OperatorUtils.plus;

import java.math.BigDecimal;
import ru.itmo.anokhin.testing.lab1.task1S.function.MathFunction;
import ru.itmo.anokhin.testing.lab1.task1S.function.Number;

public class Multiply implements BinaryOperator {

  @Override
  public MathFunction apply(MathFunction first, MathFunction second) {
    return new MathFunction() {
      @Override
      public Number evaluate(MathFunction... arguments) {
        final BigDecimal multiplied = first.evaluate(arguments).getValue();
        final BigDecimal multiplicand = second.evaluate(arguments).getValue();
        return new Number(multiplied.multiply(multiplicand));
      }

      @Override
      public MathFunction derive() {
        return plus(apply(first.derive(), second), apply(first, second.derive()));
      }
    };
  }
}
