/*
package ru.itmo.anokhin.testing.lab1.task1S.function;

import java.util.stream.Stream;
import ru.itmo.anokhin.testing.lab1.task1S.constraint.ArgumentCountConstraints;

public class AbstractMathFunction implements MathFunction {

  private final ArgumentDefinition[] argumentDefinitions;
  private final ArgumentCountConstraints argumentCountConstraints;

  protected AbstractMathFunction(
      final ArgumentDefinition[] argumentDefinitions,
      final ArgumentCountConstraints argumentCountConstraints
  ) {
    this.argumentDefinitions = argumentDefinitions;
    this.argumentCountConstraints = argumentCountConstraints;
  }

  protected ArgumentDefinition[] getArgumentDefinition(int order) {
    return argumentDefinitions;
  }

  protected ArgumentCountConstraints getArgumentCountConstraints() {
    return argumentCountConstraints;
  }

  private void checkArgs() {
    if(!argumentCountConstraints.matches(argumentDefinitions.length)) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public Number evaluate(MathFunction... arguments) {
    Stream.of(arguments).allMatch(arg -> arg instanceof Number || arg instanceof Literal);
  }

  @Override
  public MathFunction derive() {
    return null;
  }
}
*/
