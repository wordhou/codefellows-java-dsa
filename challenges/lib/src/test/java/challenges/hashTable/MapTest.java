package challenges.hashTable;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public abstract class MapTest<K, V, T extends Map<K, V>> {
    private T map;
    private List<K> keys;
    private List<V> values;

    protected abstract T createInstance();
    protected abstract List<K> getDistinctKeys();
    protected abstract List<V> getDistinctValues();

    @Before
    public void setUp() {
        map = createInstance();
        keys = getDistinctKeys();
        values = getDistinctValues();
    }

    @Test
    public void isEmpty() {
        assertTrue("newly created map is empty", map.isEmpty());
        V value = values.get(0);
        K key = keys.get(0);

        map.add(key, value);
        assertFalse("map with entry is not empty", map.isEmpty());

        map.remove(key);
        assertTrue("map with entry that gets removed is empty", map.isEmpty());
    }

    @Test
    public void sizeWithUniqueKeys() {
        assertEquals("newly created map has size", 0, map.size());
        V value = values.get(0);
        int size = 0;
        for (K key : keys) {
            size++;
            map.add(key, value);
            assertEquals(String.format("after %d insertions map has size", size), size, map.size());
        }

        for(K key : keys) {
            size--;
            map.remove(key);
            assertEquals("after removals map has size", size, map.size());
        }
    }

    @Test
    public void sizeWithDuplicates() {
        assertEquals("newly created map has size", 0, map.size());
        V value = values.get(0);
        V value2 = values.get(1);
        int size = 0;
        for (K key : keys) {
            size++;
            map.add(key, value);
            map.add(key, value2);
            assertEquals(String.format("after %d insertions map has size", size), size, map.size());
        }

        for(K key : keys) {
            size--;
            map.remove(key);
            map.remove(key);
            assertEquals("after removals map has size", size, map.size());
        }
    }

    @Test
    public void addAndRemoveAndContainsAndGet() {
        final int pairs = Math.min(keys.size(), values.size());

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            V value = values.get(i);
            map.add(key, value);
            assertTrue("after adding key-value pair map contains key", map.containsKey(key));
            assertEquals("after adding key-value pair map gets back value", value, map.get(key));
        }

        for (int i = pairs - 1; i >= 0; i--) {
            K key = keys.get(i);
            map.remove(key);
            assertFalse("after removing key-value pair map doesn't contain key", map.containsKey(key));
            assertNull("after removing key-value pair getting the key returns null", map.get(key));
        }
    }

    @Test
    public void removeAndContainsAndGetReversedOrder() {
        final int pairs = Math.min(keys.size(), values.size());

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            V value = values.get(i);
            map.add(key, value);
        }

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            map.remove(key);
            assertFalse("after removing key-value pair map doesn't contain key", map.containsKey(key));
            assertNull("after removing key-value pair getting the key returns null", map.get(key));
        }
    }

    @Test
    public void removeRandomOrder() {
        final int pairs = Math.min(keys.size(), values.size());

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            V value = values.get(i);
            map.add(key, value);
        }

        Collections.shuffle(keys);

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            map.remove(key);
            assertFalse("after removing key-value pair map doesn't contain key", map.containsKey(key));
            assertNull("after removing key-value pair getting the key returns null", map.get(key));
        }
    }

    @Test
    public void addAndUpdate() {
        final int pairs = Math.min(keys.size(), values.size());

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            V value = values.get(i);
            map.add(key, value);
        }

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            V value = values.get(pairs - i - 1);
            map.add(key, value);
        }

        for (int i = 0; i < pairs; i++) {
            K key = keys.get(i);
            V value = values.get(pairs - i - 1);
            assertEquals("map gets value after updating key-value pair", value, map.get(key));
        }
    }

    @Test
    public void getNull() {
        keys.forEach(k -> assertNull("getting key that doesn't exist returns null", map.get(k)));
    }
}