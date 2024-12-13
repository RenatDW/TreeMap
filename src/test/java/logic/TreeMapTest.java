package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TreeMapTest {
    TreeMap<Integer, Integer> tr = new TreeMap<>();

    @Test
    void clear() {
        tr.put(1,2);
        tr.put(2,2);
        tr.put(3,2);
        tr.put(4,2);
        tr.put(5,2);
        tr.clear();
        assertNull(tr.root);
    }

    @Test
    void containsKey() {
        tr.put(1,2);
        tr.put(2,2);
        tr.put(3,2);
        tr.put(4,2);
        tr.put(5,2);
        assertTrue(tr.containsKey(1));
        assertTrue(tr.containsKey(5));
    }

    @Test
    void containsValue() {
        tr.put(1,1);
        tr.put(2,2);
        tr.put(3,3);
        tr.put(4,4);
        tr.put(5,25);
        assertTrue(tr.containsValue(1));
        assertTrue(tr.containsValue(25));
    }

    @Test
    void isEmpty() {
        tr.clear();
        assertTrue(tr.isEmpty());
        tr = new TreeMap<Integer,Integer>();
        assertTrue(tr.isEmpty());

    }

    @Test
    void get() {
        tr.put(1,1);
        tr.put(2,2);
        assertEquals(tr.get(1), 1);
    }

    @Test
    void getOrDefault() {
        tr.put(1,1);
        tr.put(2,2);
        assertNull(tr.getOrDefault(4, null));
    }

    @Test
    void put() {
        assertDoesNotThrow(() -> tr.put(1,1));
        assertDoesNotThrow(() -> tr.put(2,1));
        assertDoesNotThrow(() -> tr.put(13,1));
    }

    @Test
    void keySet() {
        tr.put(1,1);
        tr.put(2,2);
        tr.put(3,3);
        tr.put(4,4);
        tr.put(5,5);
        assertEquals(tr.keySet(), new HashSet<>(Arrays.asList(1,2,3,4,5)));
    }

    @Test
    void values() {
        tr.put(1,1);
        tr.put(2,2);
        tr.put(3,3);
        tr.put(4,4);
        tr.put(5,5);
        assertEquals(tr.values(), Arrays.asList(1,2,3,4,5));
    }


    @Test
    void putAll() {
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
        tr.put(1,1);
        tr.put(1,1);
        tr.put(3,1);
        tr.put(4,1);
        tr.put(5,1);
        tr.remove(1);
        assertEquals(tr.size, 3);
    }
}