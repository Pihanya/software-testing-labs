package ru.itmo.anokhin.testing.lab1.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class ScriptTest {

  private ActionService actionService = mock(ActionService.class);

  private final Script script = new Script(actionService);

  private ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

  @Test
  public void verifySequence() {
    script.execute();

    verify(actionService, times(1)).triggerEngineSwitch();
    verify(actionService, times(1)).performWindBlowing();
    verify(actionService, times(1)).fireHeroesIntoSpace(captor.capture());

    final List<String> heroes = captor.getValue();
    assertEquals(2, heroes.size());
    assertEquals("Форд", heroes.get(0));
    assertEquals("Артур", heroes.get(1));
  }
}
