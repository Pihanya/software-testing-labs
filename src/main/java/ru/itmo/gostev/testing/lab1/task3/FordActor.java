package ru.itmo.gostev.testing.lab1.task3;

import static ru.itmo.gostev.testing.lab1.task3.ActionResultMessages.HAS_SHRUG_SHOLDERS;
import static ru.itmo.gostev.testing.lab1.task3.ActionResultMessages.THOUGHT_SOMETHING_A_NONSENSE;

public class FordActor implements Actor {

  @Override
  public String getDisplayedName() {
    return "Форд";
  }

  public ActionResult thinkUpSomethingIsANonsense() {
    final String message = String.format(THOUGHT_SOMETHING_A_NONSENSE, this.getDisplayedName());
    return new ActionResult(this, message);
  }

  public ActionResult shrugShoulders() {
    final String message = String.format(HAS_SHRUG_SHOLDERS, this.getDisplayedName());
    return new ActionResult(this, message);
  }
}
