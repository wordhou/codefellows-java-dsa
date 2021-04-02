package challenges.stacksQueues;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;

public interface Queue<T> {
    boolean isEmpty();
    @Nullable
    T peek();
    void enqueue(T item);
    T dequeue() throws NoSuchElementException;
}
