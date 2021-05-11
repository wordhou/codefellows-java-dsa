package challenges.graph.interfaces;

public interface IntWeightedIntGraph {
    void resize(int size);

    void addEdge(int from, int to, int weight);

    NeighborsAndWeights neighborsWithWeight(int vertex);

    int[] neighbors(int vertex);

    boolean neighbors(int from, int to);

    int size();

    class NeighborsAndWeights {
        int[] neighbors;
        int[] weights;

        public NeighborsAndWeights(int[] neighbors, int[] weights) {
            this.neighbors = neighbors;
            this.weights = weights;
        }

        public int[] getNeighbors() {
            return neighbors;
        }

        public int[] getWeights() {
            return weights;
        }
    }
}
