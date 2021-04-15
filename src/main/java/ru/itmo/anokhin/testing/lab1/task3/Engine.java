package ru.itmo.anokhin.testing.lab1.task3;

public class Engine {

  static Engine INSTANCE = new Engine();

  private boolean turnedOn;

  public Engine() {
    this(false);
  }

  public Engine(boolean initialState) {
    this.turnedOn = initialState;
  }

  public ActionResult toggleEngine() {
    turnedOn = !turnedOn;

    return turnedOn
        ? new ActionResult(this.toString(), String.format(ActionResultMessages.ENGINE_TURNED_ON, this.toString()))
        : new ActionResult(this.toString(), String.format(ActionResultMessages.ENGINE_TURNED_OFF, this.toString()));
  }

  @Override
  public String toString() {
    return "Мотор";
  }
}
