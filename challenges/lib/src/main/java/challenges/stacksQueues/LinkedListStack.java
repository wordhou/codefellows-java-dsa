package challenges.stacksQueues;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
    Node<T> head;

    public boolean isEmpty() {
        return head == null;
    }

    @Nullable
    public T peek() {
        return head == null ? null : head.item;
    }

    public void push(T item) {
        head = new Node<>(item, head);
    }

    public T pop() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException("Can't pop an empty Stack");
        T item = head.item;
        head = head.next;
        return item;
    }
}