package ru.itmo.gostev.testing.lab1.task3;

import java.util.List;

public class TreasuresSavedPossibility implements Possibility, Actor {

  private final double baseProbability;

  public TreasuresSavedPossibility(final double baseProbability) {
    this.baseProbability = baseProbability;
  }

  public double getBaseProbability() {
    return baseProbability;
  }

  @Override
  public String getSubjectName() {
    return "Сохранившееся богатсва";
  }

  @Override
  public String getDescription() {
    return "В форме представляющей какой-либо интерес";
  }

  @Override
  public PossibilityType getType() {
    return PossibilityType.OF_EXISTENCE;
  }

  @Override
  public double getProbability(List<Assumption> assumptions) {
    return baseProbability * assumptions.size();
  }

  @Override
  public String getDisplayedName() {
    return getSubjectName() + " " + getDescription();
  }
}
