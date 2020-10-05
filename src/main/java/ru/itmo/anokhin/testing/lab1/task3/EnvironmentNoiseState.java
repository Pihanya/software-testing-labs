package ru.itmo.anokhin.testing.lab1.task3;

import java.util.List;

public class EnvironmentNoiseState {

  private int volume;

  private final NoiseSource[] levels;

  public EnvironmentNoiseState(NoiseSource[] levels) {
    this.volume = 0;
    this.levels = levels;
  }

  ActionResult increaseVolume() {
    return setVolume(volume + 1);
  }

  public ActionResult setVolume(final int volume) {
    final NoiseSource oldSource = this.volume == 0 ? null : levels[this.volume - 1];

    if(volume > levels.length) {
      this.volume = levels.length;
      return new ActionResult(levels[this.volume - 1].getName(), String.format(ActionResultMessages.MAX_VOLUME, levels[this.volume - 1]));
    } else {
      this.volume = volume;
    }

    final NoiseSource newSource = levels[this.volume - 1];
    if(oldSource == null) {
      return new ActionResult(
          newSource.getName(), String.format(ActionResultMessages.VOLUME_BEGIN, newSource)
      );
    } else {
      return new ActionResult(
          List.of(oldSource.getName(), newSource.getName()), String.format(ActionResultMessages.VOLUME_CHANGE, oldSource, newSource)
      );
    }
  }

  public int getVolume() {
    return volume;
  }
}
