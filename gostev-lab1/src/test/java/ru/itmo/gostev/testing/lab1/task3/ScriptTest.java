package ru.itmo.gostev.testing.lab1.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ScriptTest {

  @Mock
  AssumptionService assumptionService;

  @Mock
  FordActor fordActor;

  @Mock
  TreasuresSavedPossibility treasuresSavedPossibility;

  @InjectMocks
  private Script script;

  private ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

  @Test
  public void verifySequence() {
    script.execute();

    script.execute();

    verify(fordActor, times(1)).thinkUpSomethingIsANonsense();

    verify(assumptionService, times(1)).makeAnAssumptionForCivilizationEverExistedThere();
    verify(assumptionService, times(1)).makeAnAssumptionForCivilizationsTurnedIntoDust();
    verify(assumptionService, atLeast(1)).createManyUnlikelyThings();

    verify(treasuresSavedPossibility, times(1)).getProbability(captor.capture());

    final List<Assumption> assumptions = captor.getValue();
    assertEquals(AssumptionService.MANY_ASSUMPTIONS + 2, assumptions.size());

    verify(fordActor, times(1)).shrugShoulders();
  }

}
