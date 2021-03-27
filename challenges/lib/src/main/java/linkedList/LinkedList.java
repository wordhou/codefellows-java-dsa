package linkedList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedList<T> implements Iterable<T> {
    @Nullable
    Node<T> head;
    @Nullable
    Node<T> tail;
    private int numElements;

    LinkedList() {
        numElements = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(T item) {
        if (isEmpty()) {
            tail = new Node<>(item, null);
            head = tail;
            numElements++;
            return;
        }
        head = new Node<>(item, this.head);
        numElements++;
    }

    public void insert(Iterable<T> items) {
        for (T item : items) insert(item);
    }

    public void append(T item) {
        if (isEmpty()) {
            insert(item);
            return;
        }
        assert tail != null;
        Node<T> newNode = new Node<>(item, null);
        tail.next = newNode;
        tail = newNode;
        numElements++;
    }

    public void append(Iterable<T> items) {
        for (T item : items) append(item);
    }

    public T pop() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Cannot pop empty list");
        assert head != null; // Guaranteed by .isEmpty() check;
        T item = head.item;
        head = head.next;
        numElements--;
        return item;
    }

    public T removeFromEnd() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Cannot pop empty list");
        assert head != null;
        Node<T> node = head;
        if (node.next == null) {
            T item = node.item;
            head = null;
            tail = null;
            numElements--;
            return item;
        }
        while (node.next.next != null) node = node.next;
        T item = node.next.item;
        tail = node;
        node.next = null;
        numElements--;
        return item;
    }

    public boolean includes(T item) {
        if (isEmpty()) return false;
        assert head != null;
        for (Node<T> node = head; node != null; node = node.next) {
            if (node.item.equals(item)) return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> node = head; node != null; node = node.next)
            sb.append(String.format("{ %s } -> ", node.item.toString()));
        sb.append("NULL");
        return sb.toString();
    }

    public void clear() {
        head = null;
        tail = null;
        numElements = 0;
    }

    public int size() {
        return numElements;
    }

    public T get(int i) throws IndexOutOfBoundsException {
        return getNode(i).item;
    }

    public void set(int i, T item) throws IndexOutOfBoundsException {
        getNode(i).item = item;
    }

    private Node<T> getNode(int i) throws IndexOutOfBoundsException {
        if (i >= this.numElements || i < 0)
            throw new IndexOutOfBoundsException("List does not have an element " + i);
        Node<T> node = head;
        for (int j = 0; j < i; j++) {
            assert node != null;
            node = node.next;
        }
        return node;
    }

    @Override @Nonnull
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> node = head;
        while (node != null) {
            action.accept(node.item);
            node = node.next;
        }
    }
}

class Node<T> {
    T item;
    Node<T> next;

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }

    public String toString() {
        if (next == null) return String.format("{ %s } -> NULL", item.toString());
        else return String.format("{ %s } -> %s", item.toString(), next.toString());
    }

    /**
     * Changes the current next pointer to point to the next of the next. Does not update the size of the LinkedList
     */
    public void removeNext() throws NoSuchElementException {
        if (next == null) throw new NoSuchElementException("Node has no next element");
        next = next.next;
    }
}

class LinkedListIterator<T> implements Iterator<T> {
    Node<T> node;

    public LinkedListIterator(Node<T> node) {
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public T next() {
        T item = node.item;
        node = node.next;
        return item;
    }
}

class NoSuchElementException extends Exception {
    public NoSuchElementException(String message) {
        super(message);
    }
}