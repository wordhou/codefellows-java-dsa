package challenges.graph.interfaces;

import java.util.Collection;

public interface Traversable<T> {
    Collection<T> neighbors(T vertex);

    Collection<T> getVertices();

    boolean contains(T vertex);
}
