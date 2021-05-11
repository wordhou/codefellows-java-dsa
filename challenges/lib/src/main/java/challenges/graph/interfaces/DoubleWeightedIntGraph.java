package challenges.graph.interfaces;

public interface DoubleWeightedIntGraph {
    void resize(int size) throws Exception;

    void addEdge(int from, int to, double weight);

    DoubleWeightedIntGraph.NeighborsAndWeights neighborsWithWeight(int vertex);

    int[] neighbors(int vertex);

    boolean neighbors(int from, int to);

    int size();

    boolean isDirected();

    class NeighborsAndWeights {
        int[] neighbors;
        double[] weights;

        public NeighborsAndWeights(int[] neighbors, double[] weights) {
            this.neighbors = neighbors;
            this.weights = weights;
        }

        public int[] getNeighbors() {
            return neighbors;
        }

        public double[] getWeights() {
            return weights;
        }
    }
}
