package ru.itmo.anokhin.testing.lab1.task3;

import java.util.ArrayList;
import java.util.List;

public class Script {
  private ActionService actionService;

  public Script(ActionService actionService) {
    this.actionService = actionService;
  }

  public List<ActionResult> execute() {
    final List<ActionResult> results = new ArrayList<>();

    results.add(actionService.triggerEngineSwitch());
    results.add(actionService.performWindBlowing());
    results.add(actionService.fireHeroesIntoSpace(List.of("Форд", "Артур")));

    return results;
  }
}
