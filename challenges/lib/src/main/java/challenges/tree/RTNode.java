package challenges.tree;

import challenges.linkedList.LinkedList;

public class RTNode<T> {
    public T value;
    public LinkedList<RTNode<T>> children;

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof RTNode)) return false;
        RTNode o = (RTNode<?>) other;
        return (value == null && o.value == null || value != null && value.equals(o.value)) && this.children.equals(o.children);
    }

    public RTNode() {
        children = new LinkedList<>();
    }

    public RTNode(T value) {
        this.value = value;
        children = new LinkedList<>();
    }

    public RTNode(T value, LinkedList<RTNode<T>> children) {
        this.value = value;
        this.children = children;
    }
}
