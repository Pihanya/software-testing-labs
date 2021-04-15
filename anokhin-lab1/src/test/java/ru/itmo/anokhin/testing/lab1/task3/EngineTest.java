package ru.itmo.anokhin.testing.lab1.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EngineTest {

  @Test
  public void turnOffEngine() {
    final ActionResult actionResult = new Engine(true).toggleEngine();

    assertEquals(1, actionResult.getActors().size(), "Must be only one actor");
    assertEquals("Мотор", actionResult.getActors().get(0), "Actor must be engine");

    assertEquals(String.format(ActionResultMessages.ENGINE_TURNED_OFF, Engine.INSTANCE.toString()), actionResult.getMessage());
  }

  @Test
  public void turnOnEngine() {
    final ActionResult actionResult = new Engine(false).toggleEngine();

    assertEquals(1, actionResult.getActors().size(), "Must be only one actor");
    assertEquals("Мотор", actionResult.getActors().get(0), "Actor must be engine");

    assertEquals(String.format(ActionResultMessages.ENGINE_TURNED_ON, Engine.INSTANCE.toString()), actionResult.getMessage());
  }
}
