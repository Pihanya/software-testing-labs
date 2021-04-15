package ru.itmo.gostev.testing.lab1.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import ru.itmo.gostev.testing.lab1.task2.TestCase.Action;
import ru.itmo.gostev.testing.lab1.task2.TestCase.Entry;
import ru.itmo.gostev.testing.lab1.task2.TestCase.Query;

@TestInstance(Lifecycle.PER_CLASS)
public class BinaryTreeTest {

  private ObjectMapper mapper;

  private BinaryTreeImpl<Integer, String> tree;

  @BeforeAll
  public void init() {
    this.mapper = new ObjectMapper().findAndRegisterModules();
  }

  @BeforeEach
  public void setUp() {
    tree = new BinaryTreeImpl<>();
  }

  @Test
  void positiveNullRootAfterInitialized() {
    assertNull(tree.rootNode);
  }

  @ParameterizedTest
  @ValueSource(
      strings = {"test_find_short_circuit", "test_find_medium_circuit"}
  )
  void parametrized(final String testCaseName) {
    final TestCase testCase = loadTestCase(testCaseName);
    final BinaryTreeImpl<Integer, String> tree = new BinaryTreeImpl<>();
    performTestCase(testCase, tree);
  }

  void performTestCase(final TestCase testCase, final BinaryTreeImpl<Integer, String> tree) {
    for (Entry<Integer, String> entry : testCase.getEntries()) {
      tree.put(entry.getKey(), entry.getValue());
    }

    final Query query = testCase.getQuery();
    final Entry<Integer, String> entry = testCase.getQueryEntry();

    final BinaryNode<Integer, String> rootNode = tree.rootNode;

    final List<BinaryNode<Integer, String>> path = gatherPath(rootNode, testCase.getActions());

    tree.rootNode = spy(tree.rootNode); 
    path.set(0, spy(tree.rootNode));

    if (query == Query.FIND) {
      tree.find(entry.getKey());
    } else if (query == Query.PUT) {
      tree.put(entry.getKey(), entry.getValue());
    }

    ArgumentCaptor<BinaryNode> putCaptor = ArgumentCaptor.forClass(BinaryNode.class);
    int counter = 0;
    for (final BinaryNode<Integer, String> node : path) {
      final Action action = testCase.getActions().get(counter++);
      switch (action) {
        case GO_LEFT:
          verify(node, times(1)).getLeftChild();
          break;
        case GO_RIGHT:
          verify(node, times(1)).getRightChild();
          break;
        case PUT_LEFT: {
          verify(node, times(1)).setLeftChild(putCaptor.capture());

          final BinaryNode<Integer, String> captured = putCaptor.getValue();
          assertEquals(entry.getKey(), captured.getKey());
          assertEquals(entry.getValue(), captured.getValue());

          break;
        }
        case PUT_RIGHT: {
          verify(node, times(1)).setRightChild(putCaptor.capture());

          final BinaryNode<Integer, String> captured = putCaptor.getValue();
          assertEquals(entry.getKey(), captured.getKey());
          assertEquals(entry.getValue(), captured.getValue());

          break;
        }
      }
    }
  }

  List<BinaryNode<Integer, String>> gatherPath(final BinaryNode<Integer, String> root, final List<Action> actions) {
    final List<BinaryNode<Integer, String>> path = new ArrayList<>();

    BinaryNode<Integer, String> current = root, c;
    for(final Action action : actions) {
      switch (action) {
        case GO_LEFT:
          c = current;
          current = current.leftChild;

          c.leftChild = spy(c.leftChild);
          path.add(c);
          break;
        case GO_RIGHT:
          c = current;
          current = current.rightChild;

          c.rightChild = spy(c.rightChild);
          path.add(c);
          break;
        case PUT_LEFT:
        case PUT_RIGHT:
          break;
      }
    }

    return path;
  }

  @Test
  void performTestCase() {
    tree.put(10, null);

  }

  TestCase loadTestCase(final String testCaseName) {
    try {
      return mapper.readValue(caseNameToFileUrl(testCaseName), TestCase.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private URL caseNameToFileUrl(final String testCaseName) {
    final String CASES_LOCATION_FORMAT = "task2/%s.json";
    return this.getClass().getClassLoader().getResource(String.format(CASES_LOCATION_FORMAT, testCaseName));
  }
}
