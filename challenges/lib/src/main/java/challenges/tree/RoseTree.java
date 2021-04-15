package challenges.tree;

import java.util.LinkedList;
import challenges.stacksQueues.LinkedListQueue;
import challenges.stacksQueues.Queue;
import challenges.stacksQueues.LinkedListStack;
import challenges.stacksQueues.Stack;

import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class RoseTree<T> {
    public RTNode<T> root;

    public RoseTree(RTNode<T> root) {
        this.root = root;
    }

    public RoseTree() {
    }

    public void breadthFirstTraversal(Consumer<? super T> f) {
        if (root == null) return;
        Queue<RTNode<T>> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            RTNode<T> current = queue.dequeue();
            f.accept(current.value); // Operate on the value before operating on any of its children
            current.children.forEach(queue::enqueue);
        }
    }

    public <S> S breadthFirstTraversal(BiFunction<? super S, T, ? extends S> f, S init) {
        if (root == null) return init;
        S value = init;
        Queue<RTNode<T>> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            RTNode<T> current = queue.dequeue();
            value = f.apply(value, current.value); // Operate on the value before operating on any of its children
            current.children.forEach(queue::enqueue);
        }
        return value;
    }

    public T breadthFirstTraversal(BinaryOperator<T> f) {
        if (root == null) throw new NoSuchElementException("No elements in tree");
        T value = null;
        Queue<RTNode<T>> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            RTNode<T> current = queue.dequeue();
            value = value == null ? current.value : f.apply(value, current.value);
            current.children.forEach(queue::enqueue);
        }
        return value;
    }

    public LinkedList<T> breadthFirstEnumeration() {
        LinkedList<T> result = new LinkedList<>();
        breadthFirstTraversal(v -> result.add(v));
        return result;
    }

    public LinkedList<T> preOrderDepthFirstEnumeration() {
        LinkedList<T> result = new LinkedList<>();
        preOrderDepthFirstTraversal(v -> result.add(v));
        return result;
    }

    public void preOrderDepthFirstTraversal(Consumer<? super T> f) {
        if (root == null) return;
        Stack<RTNode<T>> stack = new LinkedListStack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            RTNode<T> current = stack.pop();
            f.accept(current.value); // Operate on the value before operating on any of its children
            current.children.forEach(stack::push);
        }
    }

    public <S> S preOrderDepthFirstTraversal(BiFunction<? super S, T, ? extends S> f, S init) {
        if (root == null) return init;
        S value = init;
        Stack<RTNode<T>> stack = new LinkedListStack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            RTNode<T> current = stack.pop();
            value = f.apply(value, current.value); // Operate on the value before operating on any of its children
            current.children.forEach(stack::push);
        }
        return value;
    }

    public T preOrderDepthFirstTraversal(BinaryOperator<T> f) {
        if (root == null) throw new NoSuchElementException("No elements in tree");
        T value = null;
        Stack<RTNode<T>> stack = new LinkedListStack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            RTNode<T> current = stack.pop();
            value = value == null ? current.value : f.apply(value, current.value);
            current.children.forEach(stack::push);
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof RoseTree)) return false;
        RoseTree o = (RoseTree<?>) other;
        return (root == null && o.root == null) || (root != null && root.equals(o.root));
    }

    public <S> RoseTree<S> map(Function<? super T, ? extends S> f) {
        return new RoseTree<S>(RoseTree.map(f, root));
    }

    public static <T,S> RoseTree<S> map(Function<? super T, ? extends S> f, RoseTree<T> tree) {
        return new RoseTree<S>(RoseTree.map(f, tree.root));
    }

    private static <T,S> RTNode<S> map(Function<? super T, ? extends S> f, RTNode<T> node) {
        return new RTNode<S>(f.apply(node.value), node.children.map(n -> RoseTree.map(f, n)));
    }
}

