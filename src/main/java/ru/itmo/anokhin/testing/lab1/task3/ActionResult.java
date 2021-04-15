package ru.itmo.anokhin.testing.lab1.task3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ActionResult {
  private final List<String> actors = new ArrayList<>();

  private final String message;

  public ActionResult(String actor, String message) {
    this.actors.add(actor);
    this.message = message;
  }

  public ActionResult(Collection<String> actors, String message) {
    this.actors.addAll(actors);
    this.message = message;
  }

  public List<String> getActors() {
    return actors;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActionResult that = (ActionResult) o;
    return Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }
}
