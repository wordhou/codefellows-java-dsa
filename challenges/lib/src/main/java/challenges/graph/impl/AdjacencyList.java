package challenges.graph.impl;

import challenges.graph.interfaces.Graph;

import java.util.*;

public class AdjacencyList<T> implements Graph<T> {
    private Map<T, List<T>> adjacencyLists = new HashMap<>();
    private boolean isDirected = false;

    public AdjacencyList(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public List<T> neighbors(T v) {
        if (!adjacencyLists.containsKey(v)) throw new NoSuchElementException("Vertex does not exist in graph.");
        return adjacencyLists.get(v);
    }

    @Override
    public boolean contains(T vertex) {
        return adjacencyLists.containsKey(vertex);
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
        return adjacencyLists.keySet();
    }

    public int size() {
        return adjacencyLists.size();
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    public T addVertex(T vertex) {
        if (!adjacencyLists.containsKey(vertex)) adjacencyLists.put(vertex, new LinkedList<T>());
        return vertex;
    }

    public void addEdge(T first, T second) {
        if (!adjacencyLists.containsKey(first) || !adjacencyLists.containsKey(second))
            throw new NoSuchElementException("Vertex does not exist in graph.");
        List<T> firstList = adjacencyLists.get(first);
        if (!firstList.contains(second)) {
            firstList.add(second);
            // Only add the first vertex to the second vertex's adjacency list when graph is undirected
            if (!isDirected) adjacencyLists.get(second).add(first);
        }
    }
}