package challenges.graph.interfaces;

public abstract class MutableDoubleWeightedIntGraph extends DoubleWeightedIntGraph {
    public abstract void resize(int size) throws Exception;

    public abstract void addEdge(int from, int to, double weight);
}
