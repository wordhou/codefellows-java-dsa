package linkedList;

public class LinkedList<T> {
    Node<T> head;
    //Node<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(T item) {
        head = new Node(item, this.head);
    }

    public T pop() throws EmptyListException {
        if (this.head == null) throw new EmptyListException("Cannot pop empty list");
        T item = head.item;
        head = head.next;
        return item;
    }

    public boolean includes(T item) {
        // return isEmpty() ? false : head.includes(item);
        if (this.isEmpty()) return false;
        return head.includes(item);
    }

    public String toString() {
        // return head == null ? "NULL" : head.toString();
        if (head == null) return "NULL";
        else return head.toString();
    }
}

class Node<T> {
    T item;
    Node<T> next;

    public boolean includes (T item) {
        if (this.item == item) return true;
        if (this.next == null) return false;
        return next.includes(item);
    }

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }

    public String toString() {
        if (this.next == null) return String.format ("{ %s } -> NULL", item.toString());
        else return String.format ("{ %s } -> %s", item.toString(), next.toString());
    }
}

class EmptyListException extends Exception {
    public EmptyListException(String message) {
        super(message);
    }
}