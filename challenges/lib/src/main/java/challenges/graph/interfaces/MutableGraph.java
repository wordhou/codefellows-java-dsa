package challenges.graph.interfaces;

import java.util.List;
import java.util.Set;

public abstract class MutableGraph<T> extends Graph<T> {
    public abstract T addVertex(T vertex);

    public abstract void addEdge(T vertex1, T vertex2);

    public abstract int size();

    public abstract boolean isDirected();
}
