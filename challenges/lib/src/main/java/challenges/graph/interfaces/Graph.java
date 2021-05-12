package challenges.graph.interfaces;

import challenges.stacksQueues.IntDynamicArray;
import challenges.stacksQueues.IntStack;
import org.checkerframework.checker.units.qual.C;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public abstract class Graph<T> {
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

    /**
     * Performs a topological sort on a graph.
     * @return An iterable containing the vertices of the graph in topologically sorted order. If the graph is
     * undirected or cyclic (cannot be topologically sorted), then returns null.
     */
    @Nullable
    public Iterable<T> topologicalSort() {
        return new TopologicalSort(getVertices()).solve();
    }

    private class TopologicalSort {
        private Set<T> vertices;
        private Set<T> visited;
        private Deque<T> result;

        public TopologicalSort(Collection<T> vertices) {
            this.vertices = new HashSet<>(vertices); // Clones the vertex set because we will be mutating it
            visited = new HashSet<>();
            result = new ArrayDeque<>();
        }

        @Nullable
        public Iterable<T> solve() {
            try {
                while (!vertices.isEmpty()) {
                    visit(vertices.iterator().next()); // Visit any node still in the vertices set
                }
                return result;
            } catch (CyclicGraphException e) {
                return null;
            }
        }

        private void visit(T vertex) throws CyclicGraphException {
            if (!vertices.contains(vertex)) return; // If we reach a node that's already been removed from the set, we can stop this DFS
            if (visited.contains(vertex)) throw new CyclicGraphException(); // If we arrive at an already visited set, we're in a cyclic graph

            visited.add(vertex);

            for(T v : neighbors(vertex)) visit(v);

            visited.remove(vertex);
            vertices.remove(vertex);
            result.addFirst(vertex);
        }

    }

    private static class CyclicGraphException extends Exception {
        public CyclicGraphException() { super(); }
    }
}