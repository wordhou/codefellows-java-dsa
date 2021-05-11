package challenges.graph.impl;

import challenges.graph.interfaces.DirectedGraph;

import java.util.*;

public class DirectedAdjacencyList<T> implements DirectedGraph<T> {
    Map<T, List<T>> map = new HashMap<>();
    Set<T> vertices = new HashSet<>();

    public T addVertex(T item) {
        vertices.add(item);
        map.put(item, new LinkedList<>());
        return item;
    }

    public void addEdge(T from, T to) {
        List<T> l = map.get(from);
        if (l == null || !vertices.contains(to)) throw new NoSuchElementException("Vertex is not in the graph.");
        l.add(to);
    }

    public List<T> neighbors(T item) {
        return map.get(item);
    }

    @Override
    public boolean contains(T vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean neighbors(T from, T to) {
        return false;
    }

    @Override
    public List<T> inNeighbors(T vertex) {
        return null;
    }

    @Override
    public Set<T> getVertices() {
        return vertices;
    }

    @Override
    public int size() {
        return 0;
    }
}
