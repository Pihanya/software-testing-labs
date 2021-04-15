package ru.itmo.gostev.testing.lab1.task2;

public class BinaryNode<K extends Comparable<K>, V> {
  final K key;
  final V value;

  BinaryNode<K, V> leftChild;
  BinaryNode<K, V> rightChild;

  public BinaryNode(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public BinaryNode<K, V> getLeftChild() {
    return leftChild;
  }

  public BinaryNode<K, V> getRightChild() {
    return rightChild;
  }

  public void setLeftChild(BinaryNode<K, V> leftChild) {
    this.leftChild = leftChild;
  }

  public void setRightChild(BinaryNode<K, V> rightChild) {
    this.rightChild = rightChild;
  }
}