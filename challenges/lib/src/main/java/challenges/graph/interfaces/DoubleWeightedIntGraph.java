package challenges.graph.interfaces;

public abstract class DoubleWeightedIntGraph extends IntGraph {
    public abstract DoubleWeightedIntGraph.NeighborsAndWeights neighborsWithWeight(int vertex);

    public static class NeighborsAndWeights {
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

        public int size() {
            return neighbors.length;
        }
    }
}
