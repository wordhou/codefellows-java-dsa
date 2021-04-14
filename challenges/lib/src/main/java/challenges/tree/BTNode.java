package challenges.tree;

public class BTNode<T> {
    public T value;
    public BTNode<T> left;
    public BTNode<T> right;

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof BTNode)) return false;
        BTNode o = (BTNode<?>) other;
        return (value == null && o.value == null || value != null && value.equals(o.value)) &&
                (left == null && o.left == null || left != null && left.equals(o.left)) &&
                (right == null && o.right == null || right != null && right.equals(o.right));
    }

    public BTNode(T value, BTNode<T> left, BTNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BTNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public BTNode(T value, BTNode<T> left) {
        this.value = value;
        this.left = left;
        this.right = null;
    }
}
