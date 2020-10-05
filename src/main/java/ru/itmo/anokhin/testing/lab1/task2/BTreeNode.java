package ru.itmo.anokhin.testing.lab1.task2;


import java.util.Objects;

class BTreeNode<K extends Comparable<K>, V> {

  int keysAmount; // number of children

  @SuppressWarnings("unchecked")
  final BTreeEntry<K, V>[] children = (BTreeEntry<K, V>[]) (new BTreeEntry[BTreeImpl.NODE_MAX_CHILDREN_AMOUNT]);   // the array of children

  // create a node with k children
  public BTreeNode(final int keysAmount) {
    this.keysAmount = keysAmount;
  }

  public int getKeysAmount() {
    return keysAmount;
  }

  public void setKeysAmount(final int keysAmount) {
    this.keysAmount = keysAmount;
  }

  public BTreeEntry<K, V> getChild(final int index) {
    return this.children[index];
  }

  public BTreeNode<K, V> setChild(final int index, final BTreeEntry<K, V> child) {
    children[index] = child;
    return this;
  }

  public void insert(final int index, final BTreeEntry<K, V> child) {
    if(index < keysAmount) {
      System.arraycopy(children, index, children, index + 1, keysAmount - index);
    } else if(index == keysAmount) {
      Objects.checkIndex(keysAmount, children.length);
    } else {
      throw new IllegalArgumentException();
    }

    children[index] = child;
    ++keysAmount;
  }
}
