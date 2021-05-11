package challenges.graph;

import challenges.graph.impl.AdjacencyList;
import challenges.graph.interfaces.Graph;

public class AdjacencyListTest extends GraphTest {
    @Override
    protected Graph<Integer> createInstance() {
        return new AdjacencyList<>();
    }
}