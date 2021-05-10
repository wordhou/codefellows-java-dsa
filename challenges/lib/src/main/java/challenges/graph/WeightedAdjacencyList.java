package challenges.graph;

import java.util.*;

public class WeightedAdjacencyList<T, W> implements WeightedGraph<T, W> {
    Map<T, List<VertexAndWeight<T,W>>> adjacencyLists = new HashMap<>();
    Set<T> vertices = new HashSet<>();

    public List<VertexAndWeight<T,W>> neighborsWithWeight(T v) {
        if (!vertices.contains(v)) throw new NoSuchElementException("Vertex does not exist in graph.");
        return adjacencyLists.get(v);
    }

    @Override
    public List<T> neighbors(T vertex) {
        //TODO
        return new LinkedList<>();
    }

    public Set<T> getVertices() {
        return vertices;
    }

    public int size() {
        return vertices.size();
    }

    public T addVertex(T vertex) {
        vertices.add(vertex);
        adjacencyLists.put(vertex, new LinkedList<>());
        return vertex;
    }

    public void addEdge(T first, T second, W weight) {
        if (!vertices.contains(first) || !vertices.contains(second))
            throw new NoSuchElementException("Vertex does not exist in graph.");
        List<VertexAndWeight<T,W>> firstList = adjacencyLists.get(first);
        List<VertexAndWeight<T,W>> secondList = adjacencyLists.get(second);
        if (firstList.stream().anyMatch(vw -> vw.getVertex().equals(second))) {
            firstList.stream()
                    .filter(vw -> vw.getVertex().equals(second))
                    .forEach(vw -> vw.setWeight(weight));
            secondList.stream()
                    .filter(vw -> vw.getVertex().equals(second))
                    .forEach(vw -> vw.setWeight(weight));
        } else {
            firstList.add(new VertexAndWeight<>(second, weight));
            secondList.add(new VertexAndWeight<>(first, weight));
        }
    }
}

