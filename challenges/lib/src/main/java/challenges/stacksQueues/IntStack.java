package challenges.stacksQueues;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;

public interface IntStack {
    boolean isEmpty();
    @Nullable
    int peek() throws NoSuchElementException;
    void push(int item);
    int pop() throws NoSuchElementException;
}
