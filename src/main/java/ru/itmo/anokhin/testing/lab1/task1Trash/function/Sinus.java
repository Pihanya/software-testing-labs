package ru.itmo.anokhin.testing.lab1.task1S.function;

import ru.itmo.anokhin.testing.lab1.task1S.constraint.ArgumentCountConstraints;

public class Sinus extends AbstractMathFunction {

  protected Sinus(ArgumentDefinition[] argumentDefinitions) {
    super(
        argumentDefinitions,
        ArgumentCountConstraints.builder().exactly(1)
    );
  }

  
}
