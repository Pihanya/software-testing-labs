package ru.itmo.anokhin.testing.lab1.task2;

public interface Entry<K, V> extends Comparable<Entry<K, V>> {
  K getKey();

  V getValue();

  boolean equals(Object o);

  int hashCode();
}
