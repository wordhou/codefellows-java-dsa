package challenges.graph;

import challenges.graph.impl.IntAdjacencyMatrix;
import challenges.graph.interfaces.IntGraph;

public class IntAdjacencyMatrixTest extends IntGraphTest {
    @Override
    protected IntGraph createInstance(int size) {
        return new IntAdjacencyMatrix(size);
    }
}
