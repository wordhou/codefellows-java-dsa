package challenges.graph.interfaces;

import challenges.stacksQueues.IntDynamicArray;
import challenges.stacksQueues.IntStack;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.IntConsumer;

public abstract class IntGraph {
    public abstract int size();

    public abstract int[] neighbors(int i);

    public abstract boolean neighbors(int i, int j);

    public abstract boolean isDirected();

    public void breadthFirstTraversal(int init, IntConsumer consumer) {
        Queue<Integer> queue = new LinkedList<>();
        BitSet visited = new BitSet();
        if (init >= this.size()) throw new IndexOutOfBoundsException();
        queue.add(init);
        visited.set(init);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            consumer.accept(vertex);
            int[] neighbors = this.neighbors(vertex);
            for (int v : neighbors) {
                if (!visited.get(v)) {
                    queue.add(v);
                    visited.set(v);
                }
            }
        }
    }

    public void depthFirstTraversal(int init, IntConsumer consumer) {
        IntStack stack = new IntDynamicArray();
        BitSet visited = new BitSet();
        if (init >= this.size()) throw new IndexOutOfBoundsException();
        stack.push(init);
        visited.set(init);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            consumer.accept(vertex);
            int[] neighbors = this.neighbors(vertex);
            for (int v : neighbors) {
                if (!visited.get(v)) {
                    stack.push(v);
                    visited.set(v);
                }
            }
        }
    }

    public boolean connected(int from, int to) {
        if (from >= this.size() || to >= this.size())
            throw new NoSuchElementException("Vertex is not in graph");

        IntStack stack = new IntDynamicArray();
        BitSet visited = new BitSet();
        stack.push(from);
        visited.set(from);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (vertex == to) return true;
            int[] neighbors = this.neighbors(vertex);
            for (int v : neighbors) {
                if (!visited.get(v)) {
                    stack.push(v);
                    visited.set(v);
                }
            }
        }
        return false;
    }

    /**
     * Performs a topological sort on a graph.
     * @return An iterable containing the vertices of the graph in topologically sorted order. If the graph is
     * undirected or cyclic (cannot be topologically sorted), then returns null.
     */
    @Nullable
    public int[] topologicalSort() {
        return new IntGraph.TopologicalSort(size()).solve();
    }

    private class TopologicalSort {
        private BitSet vertices;
        private BitSet visited;
        private IntDynamicArray result;

        public TopologicalSort(int size) {
            vertices = new BitSet(size);
            vertices.set(0, size);
            visited = new BitSet(size);
            result = new IntDynamicArray();
        }

        @Nullable
        public int[] solve() {
            try {
                while (!vertices.isEmpty()) {
                    visit(vertices.nextSetBit(0)); // Visit any node still in the vertices set
                }
                return result.reversed();
            } catch (CyclicGraphException e) {
                return null;
            }
        }

        private void visit(int vertex) throws CyclicGraphException {
            if (!vertices.get(vertex)) return; // If we reach a node that's already been removed from the set, we can stop this DFS
            if (visited.get(vertex)) throw new CyclicGraphException(); // If we arrive at an already visited set, we're in a cyclic graph

            visited.set(vertex);

            for(int v: neighbors(vertex)) visit(v);

            visited.clear(vertex);
            vertices.clear(vertex);
            result.add(vertex);
        }

    }

    private static class CyclicGraphException extends Exception {
        public CyclicGraphException() { super(); }
    }

}
