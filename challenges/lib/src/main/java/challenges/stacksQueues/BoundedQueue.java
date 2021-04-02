package challenges.stacksQueues;

import java.util.NoSuchElementException;

public interface BoundedQueue<T> {
    boolean isEmpty();
    boolean isFull();
    boolean offer(T item);
    void enqueue(T item) throws CollectionIsFullException;
    T dequeue() throws NoSuchElementException;
}
