package challenges.graph;

import java.util.List;
import java.util.Set;

public interface Graph<T> {
    T addVertex(T vertex);

    void addEdge(T vertex1, T vertex2);

    List<T> neighbors(T vertex);

    Set<T> getVertices();

    int size();
}
