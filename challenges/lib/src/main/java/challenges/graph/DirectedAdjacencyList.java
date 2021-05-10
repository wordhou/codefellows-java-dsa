package challenges.graph;

import java.util.*;

public class DirectedAdjacencyList<T> implements DirectedGraph<T> {
    Map<T, List<T>> map = new HashMap<>();
    Set<T> vertices = new HashSet<>();

    public void addVertex(T item) {
        vertices.add(item);
        map.put(item, new LinkedList<>());
    }

    public void addDirectedEdge(T from, T to) {
        List<T> l = map.get(from);
        if (l == null || !vertices.contains(to)) throw new NoSuchElementException("Vertex is not in the graph.");
        l.add(to);
    }

    public List<T> outNeighbors(T item) {
        return map.get(item);
    }

    @Override
    public Set<T> getVertices() {
        return vertices;
    }
}
