package ru.itmo.anokhin.testing.lab1.task2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BTreeTest {
    public void fillTreeByData(BTree<Integer> bpt) {
        bpt.add(0);
        bpt.add(1);
        bpt.add(2);
        bpt.add(3);
        bpt.add(4);
        bpt.add(5);
        bpt.add(6);
        bpt.add(7);
        bpt.add(8);
        bpt.add(9);
    }

    @Test
    public void testSizeEqualsInsertedValues() {
        BTree<Integer> bpt = new BTree<>();
        fillTreeByData(bpt);

        assertEquals(bpt.size(), 10, "Size don't equal the inserted counts");
    }

    @Test
    public void testSizeZeroAfterClear() {
        BTree<Integer> bpt = new BTree<>();
        fillTreeByData(bpt);

        bpt.clear();

        assertEquals(bpt.size(), 0, "Size isn't zero after clear");
    }

    @Test
    public void testInsertedValuesCanBeSearched() {
        BTree<Integer> bpt = new BTree<Integer>();
        fillTreeByData(bpt);

        assertTrue(bpt.contains(0), "Unable to find existed key");
        assertTrue(bpt.contains(2), "Unable to find existed key");
        assertTrue(bpt.contains(4), "Unable to find existed key");
        assertTrue(bpt.contains(6), "Unable to find existed key");
        assertTrue(bpt.contains(8), "Unable to find existed key");
    }

    @Test
    public void testInsertedValuesCantBeSearched() {
        BTree<Integer> bpt = new BTree<Integer>();
        fillTreeByData(bpt);

        bpt.remove(1);
        bpt.remove(3);
        bpt.remove(5);
        bpt.remove(7);
        bpt.remove(9);

        assertFalse(bpt.contains(1), "Removed key was found");
        assertFalse(bpt.contains(3), "Removed key was found");
        assertFalse(bpt.contains(5), "Removed key was found");
        assertFalse(bpt.contains(7), "Removed key was found");
        assertFalse(bpt.contains(9), "Removed key was found");
    }

    @Test
    public void testOutputIsTheSameAsOnSite() {
        BTree<Integer> bpt = new BTree<Integer>();
//        fillTreeByData(bpt);
        bpt.add(1);
        bpt.add(2);
        bpt.add(3);
        bpt.add(4);

        String expected = "└──3├──1,2└──4";

        String actual = bpt.toString()
                           .replace("\n", "")
                           .replace(" ", "");

        assertEquals(expected, actual);

    }
}
