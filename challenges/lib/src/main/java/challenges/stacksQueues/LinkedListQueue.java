package challenges.stacksQueues;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;

public class LinkedListQueue<T> implements Queue<T> {
    Node<T> head;
    Node<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public T peek() {
        return head == null ? null : head.item;
    }

    public void enqueue(T item) {
        if (head == null) {
            head = new Node<>(item, null);
            tail = head;
        } else {
            tail.next = new Node<>(item, null);
            tail = tail.next;
        }
    }

    public T dequeue() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();
        T item = head.item;
        head = head.next;
        if (head == null) tail = null;
        return item;
    }
}

