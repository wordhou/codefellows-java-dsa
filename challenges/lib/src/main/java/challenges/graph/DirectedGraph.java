package challenges.graph;

import java.util.List;
import java.util.Set;

public interface DirectedGraph<T> extends Graph<T> {
    T addVertex(T vertex);

    void addEdge(T vertex1, T vertex2);

    List<T> neighbors(T vertex);

    boolean neighbors(T from, T to);

    List<T> inNeighbors(T vertex);

    Set<T> getVertices();

    int size();
}
