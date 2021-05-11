package challenges.graph;

import java.util.List;
import java.util.Set;

public interface DirectedGraph<T> {
    List<T> outNeighbors(T item);
    Set<T> getVertices();
}
