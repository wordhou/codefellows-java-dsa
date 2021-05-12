package challenges.graph.interfaces;

import java.util.List;
import java.util.Set;

public interface Graph<T> extends Traversable<T> {
    T addVertex(T vertex);

    void addEdge(T vertex1, T vertex2);

    List<T> neighbors(T vertex);

    boolean neighbors(T from, T to);

    Set<T> getVertices();

    int size();

    boolean isDirected();
}
