package challenges.graph;

import org.apache.commons.math3.exception.OutOfRangeException;

import java.util.List;

/**
 * An unweighted graph on vertices labeled by integer indices. The vertices are indexed 0 to n-1 where n is the size of
 * the graph.
 */

public interface IntGraph {
    int size();

    void resize(int size) throws Exception;

    void addEdge(int i, int j);

    int[] neighbors(int i);

    boolean neighbors(int i, int j);
}