package ru.itmo.anokhin.testing.lab1.task3;

public class Whistle implements NoiseSource {

  static Whistle INSTANCE = new Whistle();

  @Override
  public String getName() {
    return "свист";
  }

  @Override
  public String getDescription() {
    return "Тоненький и неприметный";
  }

  @Override
  public String toString() {
    return getDescription() + " " + getName();
  }
}
