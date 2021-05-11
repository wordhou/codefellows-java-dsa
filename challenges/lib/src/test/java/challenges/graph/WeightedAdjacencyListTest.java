package challenges.graph;

import challenges.graph.impl.WeightedAdjacencyList;
import challenges.graph.interfaces.WeightedGraph;

public class WeightedAdjacencyListTest extends WeightedGraphTest {
    @Override
    protected WeightedGraph<Integer, Integer> createInstance() {
        return new WeightedAdjacencyList<>();
    }
}
