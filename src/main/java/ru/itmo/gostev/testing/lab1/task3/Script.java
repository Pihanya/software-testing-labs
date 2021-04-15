package ru.itmo.gostev.testing.lab1.task3;

import static ru.itmo.gostev.testing.lab1.task3.ActionResultMessages.PROBABILITY_OF_OCCASION_TO_HAPPEN;

import java.util.ArrayList;
import java.util.List;

public class Script {
  private final AssumptionService assumptionService;

  private final FordActor fordActor;

  private final TreasuresSavedPossibility treasuresSavedPossibility;

  public Script(
      final AssumptionService assumptionService,
      final FordActor fordActor,
      final TreasuresSavedPossibility treasuresSavedPossibility
  ) {
    this.assumptionService = assumptionService;
    this.fordActor = fordActor;
    this.treasuresSavedPossibility = treasuresSavedPossibility;
  }

  public List<ActionResult> execute() {
    final List<ActionResult> results = new ArrayList<>();

    results.add(fordActor.thinkUpSomethingIsANonsense());

    final Assumption civilizationExistAssumption = assumptionService.makeAnAssumptionForCivilizationEverExistedThere();
    final Assumption civilizationGetTurnedIntoDust = assumptionService.makeAnAssumptionForCivilizationsTurnedIntoDust();

    final ArrayList<Assumption> manyAssupmtions = new ArrayList<>(assumptionService.createManyUnlikelyThings());
    manyAssupmtions.add(civilizationExistAssumption);
    manyAssupmtions.add(civilizationGetTurnedIntoDust);

    final double probability = treasuresSavedPossibility.getProbability(manyAssupmtions);
    results.add(new ActionResult(
        treasuresSavedPossibility,
        String.format(PROBABILITY_OF_OCCASION_TO_HAPPEN, treasuresSavedPossibility.getDisplayedName(), probability)
    ));

    results.add(fordActor.shrugShoulders());

    return results;
  }
}
