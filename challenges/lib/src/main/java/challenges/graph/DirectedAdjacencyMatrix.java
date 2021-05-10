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

    @Override
    public Integer addVertex(Integer vertex) {
        return null;
    }

    public void addEdge(Integer from, Integer to) {
        matrix[from][to] = true;
    }

    @Override
    public List<Integer> neighbors(Integer vertex) {
        return null;
    }

    @Override
    public boolean containsVertex(Integer vertex) {
        return false;
    }

    @Override
    public boolean neighbors(Integer from, Integer to) {
        return false;
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

    @Override
    public int size() {
        return 0;
    }
}
