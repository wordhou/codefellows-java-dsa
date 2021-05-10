package challenges.graph;

public class AdjacencyListTest extends GraphTest {
    @Override
    protected Graph<Integer> createInstance() {
        return new AdjacencyList<>();
    }
}