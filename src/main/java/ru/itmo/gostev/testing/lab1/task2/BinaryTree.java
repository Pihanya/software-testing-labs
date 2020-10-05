package ru.itmo.gostev.testing.lab1.task2;

public interface BinaryTree<K extends Comparable<K>, V> {
  V find(K key);

  void put(K key, V value);
}
