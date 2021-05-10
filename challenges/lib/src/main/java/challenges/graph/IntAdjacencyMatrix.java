package challenges.graph;

import java.util.Arrays;

public class IntAdjacencyMatrix implements IntGraph {
    private boolean[][] matrix;
    private int numVertices;

    public IntAdjacencyMatrix(int size) {
        numVertices = size;
        matrix = new boolean[size][size];
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public void resize(int size) throws Exception {
        if (size < 0) throw new Exception("Graph cannot have a negative number of vertices.");
        if (size == numVertices) return;

        boolean[][] newMatrix = new boolean[size][size];
        int n = Math.min(size, numVertices);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
    }

    @Override
    public void addEdge(int i, int j) {
        matrix[i][j] = true;
        matrix[j][i] = true;
    }

    @Override
    public int[] neighbors(int i) {
        int count = 0;
        int[] result = new int[10];
        for (int j = 0; j < numVertices; j++) {
            if (matrix[i][j]) {
                if (count == result.length) {
                    result = Arrays.copyOfRange(result, 0, result.length * 2);
                }
                result[count++] = j;
            }
        }

        return Arrays.copyOfRange(result, 0, count);
    }

    @Override
    public boolean neighbors(int i, int j) {
        return matrix[i][j];
    }
}
