package ru.itmo.anokhin.testing.lab1.task1S.operator;

import static ru.itmo.anokhin.testing.lab1.task1S.util.OperatorUtils.divide;
import static ru.itmo.anokhin.testing.lab1.task1S.util.OperatorUtils.multiply;
import static ru.itmo.anokhin.testing.lab1.task1S.util.OperatorUtils.substract;

import java.math.RoundingMode;
import ru.itmo.anokhin.testing.lab1.task1S.function.MathFunction;
import ru.itmo.anokhin.testing.lab1.task1S.function.Number;

public class Divide implements BinaryOperator {

  @Override
  public MathFunction apply(MathFunction first, MathFunction second) {
    return new MathFunction() {
      @Override
      public Number evaluate(MathFunction... arguments) {
        return new Number(first.evaluate(arguments).getValue().divide(second.evaluate(second).getValue(), RoundingMode.UP));
      }

      @Override
      public MathFunction derive() {
        return divide(substract(multiply(first.derive(), second), multiply(first, second.derive())), multiply(second, second));
      }
    };
  }
}
