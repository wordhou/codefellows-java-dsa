package challenges.graph;

import java.util.Collection;

public interface Traversable<T> {
    Collection<T> neighbors(T vertex);

    boolean contains(T vertex);
}
