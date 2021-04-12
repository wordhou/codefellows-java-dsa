package challenges.bst;

import challenges.stacksQueues.LinkedListQueue;
import challenges.stacksQueues.LinkedListStack;
import challenges.stacksQueues.Queue;
import challenges.stacksQueues.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<T> {
    BTNode<T> root;

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

    public List<T> breadthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        breadthFirstTraversal(result::add);
        return result;
    }

    public List<T> preOrderDepthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        preOrderDepthFirstTraversal(result::add);
        return result;
    }

    public List<T> inOrderDepthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        inOrderDepthFirstTraversal(result::add);
        return result;
    }

    public List<T> postOrderDepthFirstEnumeration() {
        List<T> result = new ArrayList<>();
        postOrderDepthFirstTraversal(result::add);
        return result;
    }
}

class BTNode<T> {
    public T value;
    public BTNode<T> left;
    public BTNode<T> right;

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
