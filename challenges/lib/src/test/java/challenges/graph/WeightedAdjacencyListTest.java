package challenges.graph;

import challenges.graph.impl.WeightedAdjacencyList;
import challenges.graph.interfaces.MutableWeightedGraph;

public class WeightedAdjacencyListTest extends MutableWeightedGraphTest {
    @Override
    protected MutableWeightedGraph<Integer, Integer> createInstance() {
        return new WeightedAdjacencyList<>();
    }
}
