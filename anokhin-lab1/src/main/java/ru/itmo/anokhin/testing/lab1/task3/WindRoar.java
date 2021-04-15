package ru.itmo.anokhin.testing.lab1.task3;

public class WindRoar implements NoiseSource {

  static WindRoar INSTANCE = new WindRoar();

  @Override
  public String getName() {
    return "Рев воздуха";
  }

  @Override
  public String getDescription() {
    return "вырывающийся в черную пустоту, усеянную невероятно яркими светящимися точками";
  }

  @Override
  public String toString() {
    return getName() + ", " + getDescription();
  }
}
