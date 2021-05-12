package challenges.graph.interfaces;

import java.util.List;
import java.util.Set;

public abstract class Graph<T> extends Traversable<T> {
    public abstract T addVertex(T vertex);

    public abstract void addEdge(T vertex1, T vertex2);

    public abstract List<T> neighbors(T vertex);

    public abstract boolean neighbors(T from, T to);

    public abstract Set<T> getVertices();

    public abstract int size();

    public abstract boolean isDirected();
}
