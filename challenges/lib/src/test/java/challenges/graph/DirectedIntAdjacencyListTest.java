package challenges.graph;

import challenges.graph.impl.IntAdjacencyList;
import challenges.graph.interfaces.MutableIntGraph;

public class DirectedIntAdjacencyListTest extends DirectedMutableIntGraphTest {
    @Override
    protected MutableIntGraph createInstance(int size) {
        return new IntAdjacencyList(size, true);
    }
}
