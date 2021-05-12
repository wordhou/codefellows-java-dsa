package challenges.graph;

import challenges.graph.impl.IntAdjacencyMatrix;
import challenges.graph.interfaces.MutableIntGraph;

public class IntAdjacencyMatrixTest extends MutableIntGraphTest {
    @Override
    protected MutableIntGraph createInstance(int size) {
        return new IntAdjacencyMatrix(size);
    }
}
