package challenges.stacksQueues;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;

public class LinkedListQueue<T> implements Queue<T> {
    Node<T> head;
    Node<T> tail;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Nullable
    @Override
    public T peek() {
        return head == null ? null : head.item;
    }

    @Override
    public void enqueue(T item) {
        if (head == null) {
            head = new Node<>(item, null);
            tail = head;
        } else {
            tail.next = new Node<>(item, null);
            tail = tail.next;
        }
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();
        T item = head.item;
        head = head.next;
        if (head == null) tail = null;
        return item;
    }
}

