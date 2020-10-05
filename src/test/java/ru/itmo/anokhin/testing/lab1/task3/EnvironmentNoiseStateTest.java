package ru.itmo.anokhin.testing.lab1.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnvironmentNoiseStateTest {

  NoiseSource[] noiseSources;

  int maxVolume;

  EnvironmentNoiseState environmentNoiseState;

  @BeforeEach
  public void setUp() {
    noiseSources = new NoiseSource[]{Whistle.INSTANCE, WindRoar.INSTANCE};
    maxVolume = noiseSources.length;

    environmentNoiseState = new EnvironmentNoiseState(noiseSources);
  }

  @Test
  public void positiveStartNoise() {
    final ActionResult actionResult = environmentNoiseState.setVolume(1);

    assertEquals(1, actionResult.getActors().size());
    assertEquals(noiseSources[0].getName(), actionResult.getActors().get(0));

    assertEquals(String.format(ActionResultMessages.VOLUME_BEGIN, Whistle.INSTANCE.toString()), actionResult.getMessage());
  }

  @Test
  public void positiveIncreaseVolume() {
    environmentNoiseState.setVolume(1);
    final ActionResult actionResult = environmentNoiseState.increaseVolume();

    assertEquals(2, actionResult.getActors().size());
    assertEquals(noiseSources[0].getName(), actionResult.getActors().get(0));
    assertEquals(noiseSources[1].getName(), actionResult.getActors().get(1));

    assertEquals(String.format(ActionResultMessages.VOLUME_CHANGE, Whistle.INSTANCE.toString(), WindRoar.INSTANCE.toString()), actionResult.getMessage());
  }

  @Test
  public void positiveMaxVolume() {
    final ActionResult actionResult = environmentNoiseState.setVolume(maxVolume + 10);

    assertEquals(1, actionResult.getActors().size());
    assertEquals(noiseSources[1].getName(), actionResult.getActors().get(0));

    assertEquals(String.format(ActionResultMessages.MAX_VOLUME, WindRoar.INSTANCE.toString()), actionResult.getMessage());
  }
}
