package challenges.graph.impl;

import challenges.graph.interfaces.IntGraph;

import java.util.Arrays;

public class IntAdjacencyList implements IntGraph {
    final private static int INIT_CAPACITY = 1;
    private int[][] lists;
    private int[] orders;
    private int numVertices;
    private boolean isDirected;

    public IntAdjacencyList (int size, boolean isDirected) {
        numVertices = size;
        lists = new int[size][INIT_CAPACITY];
        orders = new int[size];
        for (int i = 0; i < numVertices; i++) lists[i] = new int[INIT_CAPACITY];
        this.isDirected = isDirected;
    }

    public IntAdjacencyList (int size) {
        this(size, false);
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public void resize(int size) throws Exception {
        if (size < 0) throw new Exception("Graph cannot have a negative number of vertices.");
        if (size == numVertices) return;
        lists = Arrays.copyOfRange(lists, 0, size);
    }

    @Override
    public void addEdge(int i, int j) {
        if (neighbors(i, j)) return;
        addToAdjacencyList(i, j);
        if(!isDirected) addToAdjacencyList(j, i);
    }

    private void addToAdjacencyList(int i, int j) {
        int capacity = lists[i].length;

        if (orders[i] == capacity)
            lists[i] = Arrays.copyOfRange(lists[i], 0, 2 * capacity);
        lists[i][orders[i]++] = j;
    }

    @Override
    public int[] neighbors(int i) {
        return Arrays.copyOfRange(lists[i], 0, orders[i]);
    }

    @Override
    public boolean neighbors(int i, int j) {
        int[] li = lists[i];
        int[] lj = lists[j];

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

    @Override
    public boolean isDirected() {
        return isDirected;
    }
}
