package challenges.graph.interfaces;

import org.apache.commons.math3.exception.OutOfRangeException;

import java.util.List;

/**
 * An unweighted graph on vertices labeled by integer indices. The vertices are indexed 0 to n-1 where n is the size of
 * the graph.
 */

public abstract class MutableIntGraph extends IntGraph {
    public abstract void resize(int size) throws Exception;

    public abstract void addEdge(int i, int j);
}