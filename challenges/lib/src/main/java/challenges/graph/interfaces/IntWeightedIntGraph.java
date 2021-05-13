package challenges.graph.interfaces;

public abstract class IntWeightedIntGraph extends IntGraph {
    public abstract void resize(int size);

    public abstract void addEdge(int from, int to, int weight);

    public abstract NeighborsAndWeights neighborsWithWeight(int vertex);

    public static class NeighborsAndWeights {
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
