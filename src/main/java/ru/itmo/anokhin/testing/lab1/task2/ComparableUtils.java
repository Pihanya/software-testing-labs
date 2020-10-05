package ru.itmo.anokhin.testing.lab1.task2;

class ComparableUtils {
  public static <C extends Comparable<C>> boolean lt(C value, C than) {
    return value.compareTo(than) < 0;
  }

  public static <C extends Comparable<C>> boolean leq(C value, C than) {
    return value.compareTo(than) <= 0;
  }

  public static <C extends Comparable<C>> boolean gt(C value, C than) {
    return value.compareTo(than) > 0;
  }

  public static <C extends Comparable<C>> boolean geq(C value, C than) {
    return value.compareTo(than) >= 0;
  }

  public static <C extends Comparable<C>> boolean eq(C value, C than) {
    return value.compareTo(than) == 0;
  }
}
