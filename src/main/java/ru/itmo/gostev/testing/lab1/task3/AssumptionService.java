package ru.itmo.gostev.testing.lab1.task3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AssumptionService {

  static int MANY_ASSUMPTIONS = 100;

  public AssumptionService() {
  }

  public Assumption makeAnAssumptionForCivilizationEverExistedThere() {
    return new Assumption("Существование здесь какой-либо цивилизации");
  }

  public Assumption makeAnAssumptionForCivilizationsTurnedIntoDust() {
    return new Assumption("Существовавшая здесь цивилизация превратилась в пыль");
  }

  public List<Assumption> createManyUnlikelyThings() {
    final AtomicInteger counter = new AtomicInteger(0);
    return Stream.generate(counter::getAndIncrement)
        .takeWhile(ctr -> ctr <= MANY_ASSUMPTIONS)
        .map(ctr -> new Assumption("Маловероятное вещь #" + ctr))
        .collect(Collectors.toList());
  }
}
