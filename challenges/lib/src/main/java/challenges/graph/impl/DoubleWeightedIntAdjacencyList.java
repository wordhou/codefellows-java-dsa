package challenges.graph.impl;

import challenges.graph.interfaces.DoubleWeightedIntGraph;

import java.util.Arrays;

public class DoubleWeightedIntAdjacencyList implements DoubleWeightedIntGraph {
    static final int INIT_CAPACITY = 2;

    int[][] neighbors;
    double[][] weights;
    private int[] orders;
    private int numVertices;
    private boolean isDirected;

    public DoubleWeightedIntAdjacencyList (int size, boolean isDirected) {
        numVertices = size;
        neighbors = new int[size][INIT_CAPACITY];
        weights = new double[size][INIT_CAPACITY];
        orders = new int[size];
        this.isDirected = isDirected;
    }

    public DoubleWeightedIntAdjacencyList (int size) {
        this(size, false);
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public void resize(int size) throws Exception {
        if (size < 0) throw new Exception("Graph cannot have a negative number of vertices.");
        if (size != numVertices) {
            neighbors = Arrays.copyOfRange(neighbors, 0, size);
            weights = Arrays.copyOfRange(weights, 0, size);
        }
    }

    @Override
    public void addEdge(int i, int j, double weight) {
        if (neighbors(i, j)) return;
        addToAdjacencyList(i, j, weight);
        if (!isDirected) addToAdjacencyList(j, i, weight);
    }

    private void addToAdjacencyList(int i, int j, double w) {
        int capacity = neighbors[i].length;

        if (orders[i] == capacity) {
            neighbors[i] = Arrays.copyOfRange(neighbors[i], 0, 2 * capacity);
            weights[i] = Arrays.copyOfRange(weights[i], 0, 2 * capacity);
        }
        neighbors[i][orders[i]] = j;
        weights[i][orders[i]++] = w;
    }

    @Override
    public int[] neighbors(int i) {
        return Arrays.copyOfRange(neighbors[i], 0, orders[i]);
    }

    @Override
    public NeighborsAndWeights neighborsWithWeight(int vertex) {
        int[] nbrs = Arrays.copyOfRange(neighbors[vertex], 0, orders[vertex]);
        double[] wgts = Arrays.copyOfRange(weights[vertex], 0, orders[vertex]);

        return new NeighborsAndWeights(nbrs, wgts);
    }

    @Override
    public boolean neighbors(int i, int j) {
        int[] li = neighbors[i];
        int[] lj = neighbors[j];

        if (isDirected) {
            for (int k = 0; k < orders[i]; k++) if (li[k] == j) return true;
        } else {
            if (orders[i] < orders[j]) {
                for (int k = 0; k < orders[i]; k++) if (li[k] == j) return true;
            } else {
                for (int k = 0; k < orders[j]; k++) if (lj[k] == i) return true;
            }
        }
        return false;
    }
}
