package ru.itmo.anokhin.testing.lab1.task2;

import java.util.Objects;

class BTreeEntry<K extends Comparable<K>, V> implements Entry<K, V> {

  private K key;
  private V value;
  private BTreeNode<K, V> next; // helper field to iterate over array entries

  public BTreeEntry(final K key, final V value, final BTreeNode<K, V> next) {
    this.key = key;
    this.value = value;
    this.next = next;
  }

  public K getKey() {
    return key;
  }

  public K setKey(K key) {
    this.key = key;
    return key;
  }

  public V getValue() {
    return value;
  }

  public V setValue(V value) {
    this.value = value;
    return value;
  }

  BTreeNode<K, V> getNext() {
    return next;
  }

  void setNext(BTreeNode<K, V> next) {
    this.next = next;
  }

  @Override
  public int compareTo(Entry<K, V> o) {
    return this.key.compareTo(o.getKey());
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BTreeEntry<?, ?> entry = (BTreeEntry<?, ?>) o;
    return Objects.equals(key, entry.key) &&
        Objects.equals(value, entry.value);
  }

  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    return "{" + key + ", " + value + '}';
  }
}
