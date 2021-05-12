package challenges.graph;

import challenges.graph.impl.IntAdjacencyList;
import challenges.graph.interfaces.MutableIntGraph;

public class IntAdjacencyListTest extends MutableIntGraphTest {
    @Override
    protected MutableIntGraph createInstance(int size) {
        return new IntAdjacencyList(size);
    }
}
