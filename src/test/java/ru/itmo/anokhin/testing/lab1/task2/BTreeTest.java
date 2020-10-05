package ru.itmo.anokhin.testing.lab1.task2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BTreeTest {
    public void fillTreeByData(BTree<Integer, String> bpt) {
        bpt.insert(0, "a");
        bpt.insert(1, "b");
        bpt.insert(2, "c");
        bpt.insert(3, "d");
        bpt.insert(4, "e");
        bpt.insert(5, "f");
        bpt.insert(6, "g");
        bpt.insert(7, "h");
        bpt.insert(8, "i");
        bpt.insert(9, "j");
    }

    @Test
    public void testInsertedValuesCanBeSearched() {
        BTree<Integer, String> bpt = new BTree<Integer, String>(4);
        fillTreeByData(bpt);

        assertEquals(bpt.search(0), "a");
        assertEquals(bpt.search(2), "c");
        assertEquals(bpt.search(4), "e");
        assertEquals(bpt.search(6), "g");
        assertEquals(bpt.search(8), "i");
    }

    @Test
    public void testInsertedValuesCantBeSearched() {
        BTree<Integer, String> bpt = new BTree<Integer, String>(4);
        fillTreeByData(bpt);

        bpt.delete(1);
        bpt.delete(3);
        bpt.delete(5);
        bpt.delete(7);
        bpt.delete(9);

        assertNull(bpt.search(1));
        assertNull(bpt.search(3));
        assertNull(bpt.search(5));
        assertNull(bpt.search(7));
        assertNull(bpt.search(9));
    }

    @Test
    public void testSearchRange() {
        BTree<Integer, String> bpt = new BTree<Integer, String>(4);
        fillTreeByData(bpt);

        assertArrayEquals(
                bpt.searchRange(3, BTree.RangePolicy.EXCLUSIVE, 7,
                        BTree.RangePolicy.EXCLUSIVE).toArray(), new String[] { "e",
                        "f", "g" });
        assertArrayEquals(
                bpt.searchRange(3, BTree.RangePolicy.INCLUSIVE, 7,
                        BTree.RangePolicy.EXCLUSIVE).toArray(), new String[] { "d",
                        "e", "f", "g" });
        assertArrayEquals(
                bpt.searchRange(3, BTree.RangePolicy.EXCLUSIVE, 7,
                        BTree.RangePolicy.INCLUSIVE).toArray(), new String[] { "e",
                        "f", "g", "h" });
        assertArrayEquals(
                bpt.searchRange(3, BTree.RangePolicy.INCLUSIVE, 7,
                        BTree.RangePolicy.INCLUSIVE).toArray(), new String[] { "d",
                        "e", "f", "g", "h" });
    }
}
