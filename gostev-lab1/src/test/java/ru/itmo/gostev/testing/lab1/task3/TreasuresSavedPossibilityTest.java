package ru.itmo.gostev.testing.lab1.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreasuresSavedPossibilityTest {

  private static final Double EVENT_BASE_PROBABILITY = 0.0001;

  private TreasuresSavedPossibility treasuresSavedPossibility;

  @BeforeEach
  public void setUp() {
    treasuresSavedPossibility = new TreasuresSavedPossibility(EVENT_BASE_PROBABILITY);
  }

  @Test
  void testGetProbability() {
    final AtomicInteger counter = new AtomicInteger();
    final List<Assumption> assumptions = Stream.generate(counter::getAndIncrement)
        .takeWhile(ctr -> ctr < 100)
        .map(ctr -> new Assumption("Test assumption #" + ctr))
        .collect(Collectors.toList());

    final double probability = treasuresSavedPossibility.getProbability(assumptions);
    assertEquals(EVENT_BASE_PROBABILITY * assumptions.size(), probability);
  }

  @Test
  void tesstGetDisplayName() {
    assertEquals(
        treasuresSavedPossibility.getSubjectName() + " " + treasuresSavedPossibility.getDescription(),
        treasuresSavedPossibility.getDisplayedName()
    );
  }
}
