package challenges.hashTable;

public interface Set<T> {
    boolean isEmpty();

    // forEach(Consumer<? super T> action)

    int size();

    void add(T item);

    void remove(T item);

    boolean contains(T item);
}
