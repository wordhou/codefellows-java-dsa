package challenges.graph;

public class WeightedAdjacencyListTest extends WeightedGraphTest {
    @Override
    protected WeightedGraph<Integer, Integer> createInstance() {
        return new WeightedAdjacencyList<>();
    }
}
