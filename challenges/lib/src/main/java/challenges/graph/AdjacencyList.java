package challenges.graph;

import org.junit.Test;

import java.util.*;

public class AdjacencyList<T> implements Graph<T> {
    Map<T, List<T>> adjacencyLists = new HashMap<>();
    Set<T> vertices = new HashSet<>();

    public List<T> neighbors(T v) {
        if (!vertices.contains(v)) throw new NoSuchElementException("Vertex does not exist in graph.");
        return adjacencyLists.get(v);
    }

    @Override
    public boolean contains(T vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean neighbors(T from, T to) {
        List<T> firstList = adjacencyLists.get(from);
        List<T> secondList = adjacencyLists.get(to);
        if (firstList.size() <= secondList.size())
            return firstList.contains(to);
        else return secondList.contains(from);
    }

    public Set<T> getVertices() {
        return vertices;
    }

    public int size() {
        return vertices.size();
    }

    public T addVertex(T vertex) {
        if (!vertices.contains(vertex)) adjacencyLists.put(vertex, new LinkedList<T>());
        vertices.add(vertex);
        return vertex;
    }

    public void addEdge(T first, T second) {
        if (!vertices.contains(first) || !vertices.contains(second)) throw new NoSuchElementException("Vertex does not exist in graph.");
        List<T> firstList = adjacencyLists.get(first);
        if (!firstList.contains(second)) {
            firstList.add(second);
            adjacencyLists.get(second).add(first);
        }
    }
}