package challenges.graph;

public class IntAdjacencyMatrixTest extends IntGraphTest {
    @Override
    protected IntGraph createInstance(int size) {
        return new IntAdjacencyMatrix(size);
    }
}
