package ru.itmo.anokhin.testing.lab1.task3;

import java.util.List;
import java.util.StringJoiner;

public class ActionService {

  private Engine engine;
  private EnvironmentNoiseState environmentNoiseState;

  public ActionService(Engine engine, EnvironmentNoiseState environmentNoiseState) {
    this.engine = engine;
    this.environmentNoiseState = environmentNoiseState;
  }

  ActionResult triggerEngineSwitch() {
    return engine.toggleEngine();
  }

  ActionResult performWindBlowing() {
    return environmentNoiseState.increaseVolume();
  }

  ActionResult fireHeroesIntoSpace(List<String> heroesNames) {
    final StringJoiner heroesEnumeration = new StringJoiner(" и ", "", " ");
    for(final String name : heroesNames ) {
      heroesEnumeration.add(name);
    }

    return new ActionResult(heroesNames, heroesEnumeration + ActionResultMessages.HEROES_FIRED_INTO_SPACE);
  }
}
