package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegerSetTest {
    private IntegerSet set1;
    private IntegerSet set2;

    @BeforeEach
    void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    @Test
    void testAddition() {
        set1.add(1);
        set1.add(2);
        set1.add(2); // duplicate
        assertEquals(2, set1.length());
    }

    @Test
    void testClear() {
        set1.add(1);
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    void testContains() {
        set1.add(5);
        assertTrue(set1.contains(5));
        assertFalse(set1.contains(6));
    }

    @Test
    void testLargest() {
        set1.add(3);
        set1.add(7);
        assertEquals(7, set1.largest());
        set1.clear();
        assertThrows(IllegalStateException.class, () -> set1.largest());
    }

    @Test
    void testSmallest() {
        set1.add(3);
        set1.add(7);
        assertEquals(3, set1.smallest());
        set1.clear();
        assertThrows(IllegalStateException.class, () -> set1.smallest());
    }

    @Test
    void testRemove() {
        set1.add(10);
        set1.remove(10);
        assertFalse(set1.contains(10));
    }

    @Test
    void testUnion() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.union(set2);
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }

    @Test
    void testIntersect() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.intersect(set2);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(2));
    }

    @Test
    void testDiff() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(4);
        set1.diff(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(2));
    }

    @Test
    void testComplement() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.complement(set2); // set1 becomes elements in set2 not in set1
        assertEquals(1, set1.length());
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
    }

    @Test
    void testIsEmpty() {
        assertTrue(set1.isEmpty());
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    @Test
    void testEquals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(1);
        assertTrue(set1.equals(set2));
        set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    void testToString() {
        set1.add(1); set1.add(2);
        String s = set1.toString();
        assertTrue(s.contains("1") && s.contains("2"));
    }
}
