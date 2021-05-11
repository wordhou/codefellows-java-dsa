package challenges.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DirectedAdjacencyMatrix implements DirectedGraph<Integer> {
    boolean[][] matrix;
    int size;

    public DirectedAdjacencyMatrix(int size) {
        this.size = size;
        matrix = new boolean[size][size];
    }

    @Override
    public List<Integer> outNeighbors(Integer item) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (matrix[item][i]) out.add(i);
        }
        return out;
    }

    public List<Integer> inNeighbors(Integer item) {
        List<Integer> in = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (matrix[i][item]) in.add(i);
        }
        return in;
    }

    public boolean edgeExists(Integer from, Integer to) {
        return matrix[from][to];
    }

    public void addEdge(Integer from, Integer to) {
        matrix[from][to] = true;
    }

    public void removeEdge(Integer from, Integer to) {
        matrix[from][to] = false;
    }

    @Override
    public Set<Integer> getVertices() {
        Set<Integer> vertices = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) vertices.add(i);
        return vertices;
    }
}
