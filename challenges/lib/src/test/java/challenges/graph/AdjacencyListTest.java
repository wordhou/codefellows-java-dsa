package challenges.graph;

import challenges.graph.impl.AdjacencyList;
import challenges.graph.interfaces.MutableGraph;

public class AdjacencyListTest extends MutableGraphTest {
    @Override
    protected MutableGraph<Integer> createInstance() {
        return new AdjacencyList<>(false);
    }
}