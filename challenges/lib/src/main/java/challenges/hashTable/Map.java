package challenges.hashTable;

import javax.annotation.Nullable;
import java.util.Optional;

public interface Map<K, V> {
    /**
     * Returns a boolean indicating whether the map is empty.
     */
    boolean isEmpty();

    /**
     * Returns the current number of entries in the map.
     */
    int size();

    /**
     * Adds an entry to the map with the given key and value. If an item exists with the given key, replaces the value.
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * An alias for add(key, value).
     */
    default void put(K key, V value) { add(key, value); }

    /**
     * Gets the value for the entry with the specified key, if it exists. If no entry with the key exists, returns null.
     * @param key
     */
    @Nullable
    V get(K key);

    /**
     * Returns an Optional representing a value corresponding to the specified key, if it exists.
     * @param key
     */
    default Optional<V> getOptional(K key) {
        return Optional.ofNullable(get(key));
    }

    /**
     * Removes an entry with the specified key from the map if it exists.
     * @param key
     */
    void remove(K key);

    /**
     * An alias for containsKey()
     * @param key
     */
    default boolean contains(K key) {
        return containsKey(key);
    }

    /**
     * Determines whether an entry with the specified key exists in the map.
     * @param key
     * @return boolean indicating whether a key exists in the map
     */
    boolean containsKey(K key);

    // forEach(BiConsumer<? super K,? super V> action)
}
