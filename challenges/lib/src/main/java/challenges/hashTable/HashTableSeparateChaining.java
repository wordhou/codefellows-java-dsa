package challenges.hashTable;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HashTableSeparateChaining<K, V> implements Map<K, V> {
    // When the load factor exceeds the expansion threshold, the hash table is expanded
    static final double EXPANSION_THRESHOLD = 0.75;
    // The proportion that the size of the underlying buckets array is increased by during expansion
    static final double EXPANSION_FACTOR = 1.5;
    // When the load factor drops below the expansion threshold, the hash table is contracted
    static final double CONTRACTION_THRESHOLD = 0.2;
    // An factor that the size hash table is decreased by during contraction;
    static final double CONTRACTION_FACTOR = 0.67;
    // The default capacity of the hash table if no bucket size is
    static final int DEFAULT_CAPACITY = 11;

    private int capacity;
    private final int startingCapacity;
    private int numElements = 0;
    private Object[] buckets;

    public HashTableSeparateChaining(int capacity) {
        this.capacity = capacity;
        this.startingCapacity = capacity;
        buckets = new Object[this.capacity];
    }

    public HashTableSeparateChaining() {
        this.capacity = DEFAULT_CAPACITY;
        this.startingCapacity = capacity;
        buckets = new Object[this.capacity];
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
    public void add(K key, V value) {
        if ((double) numElements / capacity > EXPANSION_THRESHOLD) expand();

        int index = key.hashCode() % capacity;
        @SuppressWarnings("unchecked")
        EntryNode<K, V> node = (EntryNode<K, V>) buckets[index];
        if (node == null) {
            buckets[index] = new EntryNode<>(key, value, null);
            numElements++;
            return;
        }

        while (true) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            if (node.next == null) {
                node.next = new EntryNode<>(key, value, null);
                numElements++;
                return;
            }
            node = node.next;
        }
    }

    @Nullable
    @Override
    public V get(K key) {
        int index = key.hashCode() % capacity;
        @SuppressWarnings("unchecked")
        EntryNode<K, V> node = (EntryNode<K, V>) buckets[index];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int index = key.hashCode() % capacity;
        @SuppressWarnings("unchecked")
        EntryNode<K, V> node = (EntryNode<K, V>) buckets[index];
        if (node == null) return;
        if (node.key.equals(key)) {
            buckets[index] = node.next;
            numElements--;
            return;
        }
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                numElements--;
                return;
            }
            node = node.next;
        }

        if (capacity > startingCapacity && (double) numElements / capacity < CONTRACTION_THRESHOLD) contract();
    }

    @Override
    public boolean containsKey(K key) {
        int index = key.hashCode() % capacity;
        @SuppressWarnings("unchecked")
        EntryNode<K, V> node = (EntryNode<K, V>) buckets[index];
        while (node != null) {
            if (node.key.equals(key)) return true;
            node = node.next;
        }
        return false;
    }

    private void expand() {
        int newCapacity = (int) (capacity * EXPANSION_FACTOR);

        Object[] bucketArray = new Object[newCapacity];
        for (Object bucket : buckets) {
            @SuppressWarnings("unchecked")
            EntryNode<K, V> node = (EntryNode<K, V>) bucket;
            while (node != null) {
                insert(bucketArray, node.key, node.value);
                node = node.next;
            }
        }

        capacity = newCapacity;
        buckets = bucketArray;
    }

    private void contract() {
        int newCapacity = (int) (capacity * CONTRACTION_FACTOR);

        Object[] bucketArray = new Object[newCapacity];
        for (Object bucket : buckets) {
            @SuppressWarnings("unchecked")
            EntryNode<K, V> node = (EntryNode<K, V>) bucket;
            while (node != null) {
                insert(bucketArray, node.key, node.value);
                node = node.next;
            }
        }

        capacity = newCapacity;
        buckets = bucketArray;
    }

    private void insert(Object[] bucketArray, K key, V value) {
        int index = key.hashCode() % bucketArray.length;
        @SuppressWarnings("unchecked")
        EntryNode<K, V> node = (EntryNode<K, V>) bucketArray[index];
        if (node == null) {
            bucketArray[index] = new EntryNode<>(key, value, null);
            return;
        }

        while(true) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }

            if (node.next == null) {
                node.next = new EntryNode<>(key, value, null);
                return;
            }

            node = node.next;
        }

    }

    static private class EntryNode<K, V> {
        @Nonnull
        K key;
        @Nonnull
        V value;
        @Nullable
        EntryNode<K, V> next;

        public EntryNode(@NotNull K key, @NotNull V value, @Nullable EntryNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
