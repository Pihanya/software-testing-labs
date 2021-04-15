package ru.itmo.gostev.testing.lab1.task3;

public class Assumption {

  private final String name;

  public Assumption(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Допущение - " + name;
  }
}
