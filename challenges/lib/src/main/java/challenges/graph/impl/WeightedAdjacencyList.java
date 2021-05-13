package challenges.graph.impl;

import challenges.graph.interfaces.MutableWeightedGraph;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class WeightedAdjacencyList<T, W> extends MutableWeightedGraph<T, W> {
    private Map<T, List<VertexAndWeight<T, W>>> adjacencyLists = new HashMap<>();
    private Set<T> vertices = new HashSet<>();
    private boolean isDirected = false;

    public WeightedAdjacencyList(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public WeightedAdjacencyList() {
        this(false);
    }

    public List<VertexAndWeight<T, W>> neighborsWithWeight(T v) {
        if (!vertices.contains(v)) throw new NoSuchElementException("Vertex does not exist in graph.");
        return adjacencyLists.get(v);
    }

    @Override
    public List<T> neighbors(T vertex) {
        return adjacencyLists.get(vertex)
                .stream()
                .map(VertexAndWeight::getVertex)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean contains(T vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean neighbors(T first, T second) {
        if (!vertices.contains(first) || !vertices.contains(second))
            throw new NoSuchElementException("Vertex does not exist in graph.");

        List<VertexAndWeight<T, W>> firstList = adjacencyLists.get(first);
        List<VertexAndWeight<T, W>> secondList = adjacencyLists.get(second);

        if (isDirected) {
            return firstList.stream().anyMatch(vw -> vw.getVertex().equals(second));
        } else {
            if (firstList.size() < secondList.size())
                return firstList.stream().anyMatch(vw -> vw.getVertex().equals(second));
            else return secondList.stream().anyMatch(vw -> vw.getVertex().equals(first));
        }
    }

    public Set<T> getVertices() {
        return vertices;
    }

    public int size() {
        return vertices.size();
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    public T addVertex(T vertex) {
        vertices.add(vertex);
        adjacencyLists.put(vertex, new LinkedList<>());
        return vertex;
    }

    public void addEdge(T first, T second, W weight) {
        if (!vertices.contains(first) || !vertices.contains(second))
            throw new NoSuchElementException("Vertex does not exist in graph.");
        List<VertexAndWeight<T, W>> firstList = adjacencyLists.get(first);
        List<VertexAndWeight<T, W>> secondList = adjacencyLists.get(second);
        setVertexAsNeighbor(first, second, weight);
        if (!isDirected) setVertexAsNeighbor(second, first, weight);
    }

    private void setVertexAsNeighbor(T first, T second, W weight) {
        List<VertexAndWeight<T, W>> list = adjacencyLists.get(first);
        list.stream()
                .filter(vw -> vw.getVertex().equals(second))
                .findAny()
                .ifPresentOrElse(
                        vw -> vw.setWeight(weight),
                        () -> list.add(new VertexAndWeight<>(second, weight))
                );
    }

    @Nullable
    @Override
    public W getWeight(T vertex1, T vertex2) {
        return adjacencyLists.get(vertex1)
                .stream()
                .filter(vw -> vw.getVertex().equals(vertex2))
                .findAny()
                .map(VertexAndWeight::getWeight)
                .orElse(null);
    }
}

