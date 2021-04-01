package challenges.linkedList;

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

    LinkedList(Iterable<T> items) {
        numElements = 0;
        this.append(items);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(T item) {
        if (isEmpty()) {
            head = new Node<>(item, null);
            tail = head;
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

    public void insertBeforeFirstOccurrence(T find, T item) throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException("Element does not exist in list.");
        if (head.item.equals(find)) {
            insert(item); // insert method updates our head reference for us
            return;
        }
        Node<T> node = head;
        while (node.next != null && !node.next.item.equals(find)) node = node.next;
        if (node.next == null) throw new NoSuchElementException("Element does not exist in list.");

        node.next = new Node<>(item, node.next);
        numElements++;
    }

    public void insertAfterFirstOccurrence(T find, T item) throws NoSuchElementException {
        Node<T> node = head;
        while (node != null && !node.item.equals(find)) node = node.next;
        if (node == null) throw new NoSuchElementException("Element does not exist in list.");

        assert (node.item == find);
        node.next = new Node<>(item, node.next);
        if (node == tail) {
            tail = node.next;
        }
        numElements++;
    }

    public void deleteFirstOccurrence(T item) throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException("Element does not exist in list.");
        if (head.item.equals(item)) {
            pop();
            return;
        }
        Node<T> node = head;
        while (node.next != null && !node.next.item.equals(item)) node = node.next;
        if (node.next == null) throw new NoSuchElementException("Element does not exist in list.");

        node.next = node.next.next;
        numElements--;
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

    public T getFromEnd(int i) throws IndexOutOfBoundsException {
        return get(size() - i - 1);
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

    public static <T> LinkedList<T> interleave(LinkedList<T> first, LinkedList<T> second) {
        LinkedList<T> result = new LinkedList<>();
        Node<T> cur1 = first.head;
        Node<T> cur2 = second.head;
        while (cur1 != null || cur2 != null) {
            if (cur1 != null) {
                result.append(cur1.item);
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                result.append(cur2.item);
                cur2 = cur2.next;
            }
        }
        return result;
    }

    @Override
    @Nonnull
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

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof LinkedList)) return false;
        LinkedList o = (LinkedList<?>) other;
        if (size() != o.size()) return false;
        Node<T> cur = head;
        Node<T> otherCur = o.head;
        while(cur != null) {
            if (!cur.item.equals(otherCur.item)) return false;
            cur = cur.next;
            otherCur = otherCur.next;
        }
        return true;
    }
}

class Node<T> {
    T item;
    Node<T> next;

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
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