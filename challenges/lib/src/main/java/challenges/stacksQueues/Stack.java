package challenges.stacksQueues;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;

public interface Stack<T> {
    boolean isEmpty();
    @Nullable
    T peek();
    void push(T item);
    T pop() throws NoSuchElementException;
}
