package ru.itmo.gostev.testing.lab1.task2;

public class BinaryTreeImpl<K extends Comparable<K>, V> implements BinaryTree<K, V> {

  BinaryNode<K, V> rootNode;

  public BinaryTreeImpl() {
  }

  BinaryTreeImpl(BinaryNode<K, V> rootNode) {
    this.rootNode = rootNode;
  }

  @Override
  public V find(K key) {
    BinaryNode<K, V> current = rootNode;
    while (current.key != key) {
      if (current.key.compareTo(key) < 0) {
        current = current.getLeftChild();
      } else {
        current = current.getRightChild();
      }

      if (current == null) {
        return null;
      }
    }
    return current.getValue();
  }

  @Override
  public void put(K key, V value) {
    final BinaryNode<K, V> node = new BinaryNode<>(key, value);
    if (rootNode == null) {
      rootNode = node;
    } else {
      BinaryNode<K, V> prev;
      BinaryNode<K, V> current = rootNode;
      while (true) {
        prev = current;

        if (key.compareTo(prev.key) < 0) {
          current = current.getLeftChild();
          if (current == null) {
            prev.setLeftChild(node);
            return;
          }
        } else {
          current = current.getRightChild();
          if (current == null) {
            prev.setRightChild(node);
            return;
          }
        }

      }
    }
  }

  @Override
  public String toString() {
    return toString(rootNode, measureHeight(rootNode, 0), "");
  }

  private String toString(final BinaryNode<K, V> node, final int treeHeight, final String indent) {
    final StringBuilder sb = new StringBuilder();
    if (indent == null || indent.isEmpty()) {
      sb.append(".\n");
    }

    if (node.getLeftChild() != null) {
      sb.append(indent).append("├──").append(' ').append(node.getLeftChild()).append('\n');
    }

    if (node.getRightChild() != null) {
      sb.append(indent).append("└──").append(' ').append(node.getRightChild()).append('\n');
    }

    if (treeHeight != 0) {
      if (node.getLeftChild() != null) {
        sb.append(toString(node.getLeftChild(), treeHeight - 1, indent + "|   "));
      } else if (node.getRightChild() != null) {
        sb.append(toString(node.getRightChild(), treeHeight - 1, indent + "    "));
      }
    }

    return sb.toString();
  }

  private int measureHeight(BinaryNode<K, V> rootNode, int current) {
    if (rootNode.getLeftChild() == null && rootNode.getRightChild() == null) {
      return current;
    }

    int left = -1;
    if (rootNode.getLeftChild() != null) {
      left = measureHeight(rootNode.getLeftChild(), current + 1);
    }

    int right = -1;
    if (rootNode.getRightChild() != null) {
      right = measureHeight(rootNode.getRightChild(), current + 1);
    }

    return Math.max(left, right);
  }
}
