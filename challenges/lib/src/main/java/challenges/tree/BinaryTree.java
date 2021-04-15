package challenges.tree;

import challenges.linkedList.LinkedList;
import challenges.stacksQueues.LinkedListQueue;
import challenges.stacksQueues.LinkedListStack;
import challenges.stacksQueues.Queue;
import challenges.stacksQueues.Stack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class BinaryTree<T> {
    public BTNode<T> root;

    public BinaryTree(BTNode<T> root) { this.root = root; }

    public void breadthFirstTraversal(Consumer<? super T> f) {
        Queue<BTNode<T>> queue = new LinkedListQueue<>();
        if (root != null) queue.enqueue(root);
        while(!queue.isEmpty()) {
            BTNode<T> current = queue.dequeue();
            f.accept(current.value); // Operate on the value when it comes out of the queue
            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }
    }

    public <S> S breadthFirstTraversal(BiFunction<? super S, T, ? extends S> f, S init) {
        S value = init;
        Queue<BTNode<T>> queue = new LinkedListQueue<>();
        if (root != null) queue.enqueue(root);
        while(!queue.isEmpty()) {
            BTNode<T> current = queue.dequeue();
            value = f.apply(value, current.value); // Operate on the value when it comes out of the queue
            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }
        return value;
    }

    public T breadthFirstTraversal(BinaryOperator<T> f) {
        if (root == null) throw new NoSuchElementException("No elements in tree");
        T value = null;
        Queue<BTNode<T>> queue = new LinkedListQueue<>();
        if (root != null) queue.enqueue(root);
        while(!queue.isEmpty()) {
            BTNode<T> current = queue.dequeue();
            value = value == null ? current.value : f.apply(value, current.value);
            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }
        return value;
    }

    public void preOrderDepthFirstTraversal(Consumer<? super T> f) {
        if (root == null) return;
        Stack<BTNode<T>> stack = new LinkedListStack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BTNode<T> current = stack.pop();
            f.accept(current.value); // Operate on the value before operating on any of its children
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    public <S> S preOrderDepthFirstTraversal(BiFunction<? super S, T, ? extends S> f, S init) {
        if (root == null) return init;
        S value = init;
        Stack<BTNode<T>> stack = new LinkedListStack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BTNode<T> current = stack.pop();
            value = f.apply(value, current.value); // Operate on the value before operating on any of its children
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
        return value;
    }

    public T preOrderDepthFirstTraversal(BinaryOperator<T> f) {
        if (root == null) throw new NoSuchElementException("No elements in tree");
        T value = null;
        Stack<BTNode<T>> stack = new LinkedListStack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BTNode<T> current = stack.pop();
            value = value == null ? current.value : f.apply(value, current.value);
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
        return value;
    }

    public void inOrderDepthFirstTraversal(Consumer<? super T> f) {
        Stack<BTNode<T>> stack = new LinkedListStack<>();
        BTNode<T> current = root;
        while(current != null || !stack.isEmpty()) {
            // Go left until you hit null, pushing nodes into the stack as you go.
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            f.accept(stack.peek().value); // Operate on the value of the node on top of the stack
            current = stack.pop().right; // Now go to the right from the last node visited.
        }
    }

    public <S> S inOrderDepthFirstTraversal(BiFunction<? super S, T, ? extends S> f, S init) {
        S value = init;
        Stack<BTNode<T>> stack = new LinkedListStack<>();
        BTNode<T> current = root;
        while(current != null || !stack.isEmpty()) {
            // Go left until you hit null, pushing nodes into the stack as you go.
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            f.apply(value, stack.peek().value); // Operate on the value of the node on top of the stack
            current = stack.pop().right; // Now go to the right from the last node visited.
        }
        return value;
    }

    public T inOrderDepthFirstTraversal(BinaryOperator<T> f) {
        T value = null;
        Stack<BTNode<T>> stack = new LinkedListStack<>();
        BTNode<T> current = root;
        while(current != null || !stack.isEmpty()) {
            // Go left until you hit null, pushing nodes into the stack as you go.
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Operate on the value of the node on top of the stack
            value = value == null ? current.value : f.apply(value, current.value);
            current = stack.pop().right; // Now go to the right from the last node visited.
        }

        return value;
    }


    public void postOrderDepthFirstTraversal(Consumer<? super T> f) {
        if (root == null) return;

        Stack<BTNode<T>> stack = new LinkedListStack<>();
        Stack<T> result = new LinkedListStack<>(); // This stack reverses the result
        BTNode<T> current;

        stack.push(root);
        while(!stack.isEmpty()) {
            current = stack.pop();
            result.push(current.value);
            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }
        while(!result.isEmpty()) f.accept(result.pop());
    }

    public <S> S postOrderDepthFirstTraversal(BiFunction<? super S, T, ? extends S> f, S init) {
        if (root == null) return init;
        S value = init;

        Stack<BTNode<T>> stack = new LinkedListStack<>();
        Stack<T> result = new LinkedListStack<>(); // This stack reverses the result
        BTNode<T> current;

        stack.push(root);
        while (!stack.isEmpty()) {
            current = stack.pop();
            result.push(current.value);
            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }
        while (!result.isEmpty()) f.apply(value, result.pop());
        return value;
    }

    public T postOrderDepthFirstTraversal(BinaryOperator<T> f) {
        if (root == null) throw new NoSuchElementException("No elements in tree");
        T value = null;

        Stack<BTNode<T>> stack = new LinkedListStack<>();
        Stack<T> result = new LinkedListStack<>(); // This stack reverses the result
        BTNode<T> current;

        stack.push(root);
        while (!stack.isEmpty()) {
            current = stack.pop();
            result.push(current.value);
            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }
        while (!result.isEmpty())
            value = value == null ? result.pop() : f.apply(value, result.pop());
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof BinaryTree)) return false;
        BinaryTree o = (BinaryTree<?>) other;
        return root.equals(o.root);
    }

    public <S> BinaryTree<S> map(Function<? super T, ? extends S> f) {
        return new BinaryTree<S>(BinaryTree.map(f, root));
    }

    public static <T,S> BinaryTree<S> map(Function<? super T, ? extends S> f, BinaryTree<T> tree) {
        return new BinaryTree<S>(BinaryTree.map(f, tree.root));
    }

    private static <T,S> BTNode<S> map(Function<? super T, ? extends S> f, BTNode<T> node) {
        if (node == null) return null;
        return new BTNode<S>(f.apply(node.value), map(f, node.left), map(f, node.right));
    }

    public T findMaximumBy(Comparator<T> comp) {
        return preOrderDepthFirstTraversal((T x, T y) -> comp.compare(x, y) > 0 ? x : y);
    }

    public List<T> breadthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        breadthFirstTraversal(e -> result.add(e));
        return result;
    }

    public List<T> preOrderDepthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        preOrderDepthFirstTraversal(e -> result.add(e));
        return result;
    }

    public List<T> inOrderDepthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        inOrderDepthFirstTraversal(e -> result.add(e));
        return result;
    }

    public List<T> postOrderDepthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        postOrderDepthFirstTraversal(e -> result.add(e));
        return result;
    }
}

