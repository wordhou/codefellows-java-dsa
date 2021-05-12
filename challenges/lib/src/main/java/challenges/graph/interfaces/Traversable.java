package challenges.graph.interfaces;

import challenges.stacksQueues.IntDynamicArray;
import challenges.stacksQueues.IntStack;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public abstract class Traversable<T> {
    public abstract Collection<T> neighbors(T vertex);

    public abstract Collection<T> getVertices();

    public abstract boolean contains(T vertex);

    public void breadthFirstTraversal(T init, Consumer<? super T> consumer) {
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        if (!this.contains(init)) throw new NoSuchElementException();
        visited.add(init);
        queue.add(init);

        while (!queue.isEmpty()) {
            T vertex = queue.remove();
            consumer.accept(vertex);
            Collection<T> neighbors = this.neighbors(vertex);
            neighbors.stream()
                    .filter(v -> !visited.contains(v))
                    .forEach(v -> {
                        queue.add(v);
                        visited.add(v);
                    });
        }
    }

    public void depthFirstTraversal(T init, Consumer<? super T> consumer) {
        Deque<T> stack = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        if (!this.contains(init)) throw new NoSuchElementException();
        visited.add(init);
        stack.push(init);

        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            consumer.accept(vertex);
            Collection<T> neighbors = this.neighbors(vertex);
            neighbors.stream()
                    .filter(v -> !visited.contains(v))
                    .forEach(v -> {
                        stack.push(v);
                        visited.add(v);
                    });
        }
    }

    public boolean connected(T from, T to) {
        if (!this.contains(from) || !this.contains(to))
            throw new NoSuchElementException("Vertex is not in graph");

        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        if (!this.contains(from)) throw new NoSuchElementException();
        visited.add(from);
        queue.add(from);

        while (!queue.isEmpty()) {
            T vertex = queue.remove();
            if (vertex.equals(from)) return true;
            Collection<T> neighbors = this.neighbors(vertex);
            neighbors.stream()
                    .filter(v -> !visited.contains(v))
                    .forEach(v -> {
                        queue.add(v);
                        visited.add(v);
                    });
        }
        return false;
    }


}