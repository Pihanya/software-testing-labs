package ru.itmo.anokhin.testing.lab1.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class BTreeTest {
  @Test
  public void test() {
    BTreeImpl<Integer, String> tree = new BTreeImpl<>();


    tree.put(10, null);
    tree.put(20, null);
    tree.put(30, null);
    tree.put(40, null);

    final List<Integer> level0Keys = Arrays.stream(tree.rootNode.children).map(q -> q.getKey()).collect(Collectors.toList());
    assertEquals(1, level0Keys.size());

    final BTreeEntry<Integer, String>[] children = tree.rootNode.children;
    assertEquals(2, children.length);

    System.out.println(tree.toString());
  }
}
