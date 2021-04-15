package ru.itmo.gostev.testing.lab1.task3;

import java.util.List;

public interface Possibility {
  String getSubjectName();

  String getDescription();

  PossibilityType getType();

  double getProbability(List<Assumption> assumptions);
}
