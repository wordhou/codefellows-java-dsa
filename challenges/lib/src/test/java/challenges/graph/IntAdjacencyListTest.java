package challenges.graph;

public class IntAdjacencyListTest extends IntGraphTest {
    @Override
    protected IntGraph createInstance(int size) {
        return new IntAdjacencyList(size);
    }
}
