package ru.itmo.gostev.testing.lab1.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_METHOD)
public class FordActorTest {

  private FordActor actor;

  @BeforeEach
  public void setUp() {
    actor = new FordActor();
  }

  @Test
  void positiveThinkUpSomethingIsANonsense() {
    final ActionResult result = actor.thinkUpSomethingIsANonsense();

    assertEquals(1, result.getActors().size());
    assertEquals(actor, result.getActors().get(0));

    final String message = result.getMessage();
    assertTrue(message.contains(actor.getDisplayedName()));
    assertTrue(areSimilar(ActionResultMessages.HAS_SHRUG_SHOLDERS, message));
  }

  @Test
  void positiveShrugShoulders() {
    final ActionResult result = actor.shrugShoulders();

    assertEquals(1, result.getActors().size());
    assertEquals(actor, result.getActors().get(0));

    final String message = result.getMessage();
    assertTrue(message.contains(actor.getDisplayedName()));
    assertTrue(areSimilar(ActionResultMessages.HAS_SHRUG_SHOLDERS, message));
  }

  boolean areSimilar(String pattern, String string) {
    return string.contains(pattern.replaceAll("%.+\\s", " "));
  }
}
