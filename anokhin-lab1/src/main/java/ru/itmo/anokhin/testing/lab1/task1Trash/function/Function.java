package ru.itmo.anokhin.testing.lab1.task1S.function;

import ru.itmo.anokhin.testing.lab1.task1S.constraint.ArgumentCountConstraints;

public class Function extends AbstractMathFunction {

  protected Function(ArgumentDefinition[] argumentDefinitions,
      ArgumentCountConstraints argumentCountConstraints) {
    super(argumentDefinitions, argumentCountConstraints);
  }
}
