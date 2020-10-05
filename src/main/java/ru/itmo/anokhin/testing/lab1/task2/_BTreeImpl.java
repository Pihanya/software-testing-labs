package ru.itmo.anokhin.testing.lab1.task2;

import static ru.itmo.anokhin.testing.lab1.task2.ComparableUtils.eq;
import static ru.itmo.anokhin.testing.lab1.task2.ComparableUtils.lt;

public class _BTreeImpl<K extends Comparable<K>, V> implements BTree<K, V> {

  // max children per B-tree node = M-1
  // (must be even and greater than 2)
  static final int NODE_MAX_CHILDREN_AMOUNT = 4;

  private BTreeNode<K, V> rootNode; // root of the B-tree
  private int treeHeight; // height of the B-tree
  private int entriesAmount; // number of key-value pairs in the B-tree

  /**
   * Initializes an empty B-tree.
   */
  public _BTreeImpl() {
    this(new BTreeNode<>(0));
  }

  _BTreeImpl(BTreeNode<K,V> rootNode) {
    this.rootNode = rootNode;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean isNotEmpty() {
    return size() != 0;
  }

  @Override
  public int size() {
    return entriesAmount;
  }

  /**
   * Returns the height of this B-tree (for debugging).
   *
   * @return the height of this B-tree
   */
  int height() {
    return treeHeight;
  }

  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException("argument to get() is null");
    }
    return search(rootNode, key, treeHeight);
  }

  public void put(final K key, final V val) {
    if (key == null) {
      throw new IllegalArgumentException("argument key to put() is null");
    }

    final BTreeNode<K, V> u = insert(rootNode, key, val, treeHeight);
    ++entriesAmount;
    if (u == null) {
      return;
    }

    // need to split root
    rootNode = new BTreeNode<K, V>(2)
        .setChild(0, new BTreeEntry<>(rootNode.getChild(0).getKey(), null, rootNode))
        .setChild(1, new BTreeEntry<>(u.getChild(0).getKey(), null, u));
    ++treeHeight;
  }

  @Override
  public boolean remove(K key) {
    return false;
  }

  /**
   * Returns a string representation of this B-tree.
   *
   * @return a string representation of this B-tree.
   */
  public String toString() {
    return toString(rootNode, treeHeight, "") + "\n";
  }

  private V search(final BTreeNode<K, V> x, final K key, final int height) {
    // external node
    if (height == 0) {
      for (int i = 0; i < x.getKeysAmount(); i++) {
        if (eq(key, x.getChild(i).getKey())) {
          return x.getChild(i).getValue();
        }
      }
    } else { // internal node
      for (int i = 0; i < x.getKeysAmount(); i++) {
        if (i + 1 == x.getKeysAmount() || lt(key, x.getChild(i + 1).getKey())) {
          return search(x.getChild(i).getNext(), key, height - 1);
        }
      }
    }
    return null;
  }

  private BTreeNode<K, V> insert(final BTreeNode<K, V> h, final K key, final V value, final int treeHeight) {
    int j;
    BTreeEntry<K, V> t = new BTreeEntry<>(key, value, null);

    // external node
    if (treeHeight == 0) {
      for (j = 0; j < h.getKeysAmount(); j++) {
        if (lt(key, h.getChild(j).getKey())) {
          break;
        }
      }
    } else { // internal node
      for (j = 0; j < h.getKeysAmount(); j++) {
        if ((j < h.getKeysAmount()) || lt(key, h.getChild(j + 1).getKey())) {
          final BTreeNode<K, V> u = insert(h.getChild(j++).getNext(), key, value, treeHeight - 1);
          if (u == null) {
            return null;
          }
          t = new BTreeEntry<>(u.getChild(0).getKey(), null, u);
          break;
        }
      }
    }

    h.insert(j, t);

    if (h.getKeysAmount() < NODE_MAX_CHILDREN_AMOUNT) {
      return null;
    } else {
      return split(h);
    }
  }

  // split node in half
  private BTreeNode<K, V> split(final BTreeNode<K, V> splitNode) {
    final int halfIndex = NODE_MAX_CHILDREN_AMOUNT / 2;
    final BTreeNode<K, V> splitTo = new BTreeNode<>(halfIndex);

    splitNode.setKeysAmount(halfIndex);
    for (int j = 0; j < halfIndex; ++j) {
      splitTo.setChild(j, splitNode.getChild(halfIndex + j));
    }

    return splitTo;
  }

  /**
   * .
   * ├── file0
   * └── foo
   *     ├── bar
   *     │   ├── file2
   *     │   ├   ├── file21
   *     │   ├   ├── file22
   *     │   └── file2
   *     └── file1
   */
  private String toString(final BTreeNode<K, V> node, final int treeHeight, final String indent) {
    final StringBuilder sb = new StringBuilder();
    if(indent == null || indent.isEmpty()) {
      sb.append(".\n");
    }

    if(treeHeight == 0) {
      for(int i = 0; i < node.getKeysAmount(); ++i) {
        String cuI;
        if(i == 0) {
          cuI = "├──";
        } else if(i + 1 < node.getKeysAmount()) {
          cuI = "├──";
        } else {
          cuI = "└──";
        }

        sb.append(indent).append(cuI).append(' ').append(node.getChild(i)).append('\n');
      }
    } else {
      for (int i = 0; i < node.getKeysAmount(); ++i) {
        if(i + 1 < node.getKeysAmount()) {
          sb.append(indent).append("├──").append(' ').append(node.getChild(i)).append('\n');
        } else {
          sb.append(indent).append("└──").append(' ').append(node.getChild(i)).append('\n');
        }

        if(i + 1 < node.getKeysAmount()) {
          sb.append(toString(node.getChild(i).getNext(), treeHeight - 1, indent + "|   "));
        } else {
          sb.append(toString(node.getChild(i).getNext(), treeHeight - 1, indent + "    "));
        }
      }
    }

    return sb.toString();
  }
}
