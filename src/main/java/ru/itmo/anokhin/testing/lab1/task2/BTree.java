package ru.itmo.anokhin.testing.lab1.task2;

public interface BTree<K extends Comparable<K>, V> {
  /**
   * Returns true if this symbol table is empty.
   *
   * @return {@code true} if this symbol table is empty; {@code false} otherwise
   */
  boolean isEmpty();

  boolean isNotEmpty();

  /**
   * Returns the number of key-value pairs in this symbol table.
   *
   * @return the number of key-value pairs in this symbol table
   */
  int size();

  /**
   * Returns the value associated with the given key.
   *
   * @param key the key
   * @return the value associated with the given key if the key is in the symbol table and {@code null} if the key is not in the symbol
   * table
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  V get(K key);

  /**
   * Inserts the key-value pair into the symbol table, overwriting the old value with the new value if the key is already in the symbol
   * table. If the value is {@code null}, this effectively deletes the key from the symbol table.
   *
   * @param key the key
   * @param val the value
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  void put(K key, V value);

  boolean remove(K key);
}
