package ru.itmo.anokhin.testing.lab1.task1S.function;

public class Literal extends Number {
  private ArgumentDefinition argumentDefinition;

  public Literal(ArgumentDefinition argumentDefinition) {
    super(argumentDefinition.getValue());
  }
}
