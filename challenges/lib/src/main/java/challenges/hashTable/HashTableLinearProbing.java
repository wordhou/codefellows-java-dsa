package challenges.hashTable;

import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class HashTableLinearProbing<K, V> implements Map<K, V> {
    // When the load factor exceeds the expansion threshold, the hash table is expanded
    static final double EXPANSION_THRESHOLD = 0.65;
    // The proportion that the size of the underlying buckets array is increased by during expansion
    static final double EXPANSION_FACTOR = 2;
    // When the load factor drops below the expansion threshold, the hash table is contracted
    static final double CONTRACTION_THRESHOLD = 0.25;
    // An factor that the size hash table is decreased by during contraction;
    static final double CONTRACTION_FACTOR = 0.5;
    // The default capacity of the hash table if no bucket size is given
    static final int DEFAULT_CAPACITY = 11;
    private final int startingCapacity;
    private Object[] buckets;
    private int numElements = 0;

    public HashTableLinearProbing(int capacity) {
        this.startingCapacity = capacity;
        buckets = new Object[startingCapacity];
    }

    public HashTableLinearProbing() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public void add(@Nonnull K key, V value) {
        if (insert(buckets, key, value)) numElements++;

        if ((double) numElements / buckets.length > EXPANSION_THRESHOLD) expand();
    }

    @Nullable
    @Override
    public V get(@Nonnull K key) {
        int index = key.hashCode() % buckets.length;

        while (buckets[index] != null) {
            @SuppressWarnings("unchecked")
            Pair<K, V> entry = (Pair<K, V>) buckets[index];
            if (entry.key == key) {
                return entry.value;
            }
            index = index + 1 == buckets.length ? 0 : index + 1;
        }
        return null;
    }

    @Override
    public void remove(@Nonnull K key) {
        int index = key.hashCode() % buckets.length;
        int emptyIndex = -1;

        while (buckets[index] != null) {
            @SuppressWarnings("unchecked")
            Pair<K, V> entry = (Pair<K, V>) buckets[index];

            if (emptyIndex >= 0) { // An entry has already been removed
                int hashIndex = entry.key.hashCode() % buckets.length;
                if (between(hashIndex, emptyIndex, index)) {
                    buckets[emptyIndex] = buckets[index];
                    buckets[index] = null;
                    emptyIndex = index;
                }
            } else if (entry.key == key) { // The entry to remove has been found
                buckets[index] = null;
                emptyIndex = index;
                numElements--;
            }

            index = index + 1 == buckets.length ? 0 : index + 1;
        }

        if (numElements > startingCapacity
                && (double) numElements / buckets.length < CONTRACTION_THRESHOLD) contract();
    }

    @Override
    public boolean containsKey(@Nonnull K key) {
        int index = key.hashCode() % buckets.length;

        while (buckets[index] != null) {
            @SuppressWarnings("unchecked")
            Pair<K, V> entry = (Pair<K, V>) buckets[index];
            if (entry.key == key) {
                return true;
            }
            index = index + 1 == buckets.length ? 0 : index + 1;
        }
        return false;
    }

    /**
     * Inserts a key-value pair into an array based on the hash of the key.
     * @param array The array to insert the key-value pair into
     * @return A boolean, returning true when a new entry is added, and false when an existing entry is updated.
     */
    private boolean insert(Object[] array, K key, V value) {
        int index = key.hashCode() % array.length;

        while (array[index] != null) {
            @SuppressWarnings("unchecked")
            Pair<K, V> entry = (Pair<K, V>) array[index];
            if (entry.key == key) {
                entry.value = value;
                return false;
            }
            index = index + 1 == array.length ? 0 : index + 1;
        }
        array[index] = new Pair<>(key, value);
        return true;
    }

    private void contract() {
        int newCapacity = (int) (buckets.length * CONTRACTION_FACTOR);

        Object[] contracted = new Object[newCapacity];
        for (Object bucket : buckets) {
            if (bucket != null) {
                @SuppressWarnings("unchecked")
                Pair<K, V> entry = (Pair<K, V>) bucket;
                insert(contracted, entry.key, entry.value);
            }
        }

        buckets = contracted;
    }

    private void expand() {
        int newCapacity = (int) (buckets.length * EXPANSION_FACTOR);

        Object[] expanded = new Object[newCapacity];
        for (Object bucket : buckets) {
            if (bucket != null) {
                @SuppressWarnings("unchecked")
                Pair<K, V> entry = (Pair<K, V>) bucket;
                insert(expanded, entry.key, entry.value);
            }
        }

        buckets = expanded;
    }

    /**
     * Checks whether or not the an index is in the range given by a start index and an end index when the indices are considered circular.
     *
     * When the start equals the end index, returns false.
     * @param start The beginning of the range, inclusive
     * @param index The index that we're checking
     * @param end   The end of the range, exclusive
     * @return A boolean that indicates whether the index is in the circular range given by start and end.
     */
    private static boolean between(int start, int index, int end) {
        return index >= start && index < end ||
                start > end && index < end ||
                start > end && index >= start;
    }

    private static class Pair<K, V> {
        private final K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
