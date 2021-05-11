package challenges.graph;

import challenges.graph.impl.IntAdjacencyList;
import challenges.graph.interfaces.IntGraph;

public class IntAdjacencyListTest extends IntGraphTest {
    @Override
    protected IntGraph createInstance(int size) {
        return new IntAdjacencyList(size);
    }
}
