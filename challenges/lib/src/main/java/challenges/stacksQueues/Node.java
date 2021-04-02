package challenges.stacksQueues;

class Node<T> {
    public Node<T> next;
    public T item;

    public Node(T item, Node<T> next) {
        this.next = next;
        this.item = item;
    }
}
