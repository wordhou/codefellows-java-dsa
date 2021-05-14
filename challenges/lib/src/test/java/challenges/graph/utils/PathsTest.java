package challenges.graph.utils;

import challenges.graph.impl.DoubleWeightedIntAdjacencyList;
import challenges.graph.impl.WeightedAdjacencyList;
import challenges.graph.interfaces.DoubleWeightedIntGraph;
import challenges.graph.interfaces.MutableDoubleWeightedIntGraph;
import challenges.graph.interfaces.MutableWeightedGraph;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PathsTest {
    final static double delta = 1e-6;
    static int[][] edges1 = {
            {0, 1, 5},
            {0, 3, 2},
            {0, 5, 5},
            {0, 7, 3},
            {1, 2, 2},
            {1, 5, 10},
            {1, 8, 5},
            {2, 4, 1},
            {3, 5, 1},
            {4, 7, 2},
            {4, 0, 1},
            {5, 8, 9},
            {8, 9, 2},
            {8, 0, 1},
            {9, 10, 14},
            {10, 5, 0},
    };

    @Test
    public void pathWeightOnDirectedGraph() {
        MutableWeightedGraph<Integer, Double> graph = new WeightedAdjacencyList<>(true);
        Arrays.asList(0,1,2,3,4,5,6,7,8,9,10).forEach(graph::addVertex);

        Arrays.asList(edges1).forEach(a -> graph.addEdge(a[0], a[1], (double) a[2]));

        assertNull(Paths.pathWeight(graph, Arrays.asList(0,1,3)));
        assertEquals(5.0, Paths.pathWeight(graph, Arrays.asList(0,1)), delta);
        assertEquals(30.0, Paths.pathWeight(graph, Arrays.asList(0,5,8,9,10,5)), delta);
        assertEquals(50.0, Paths.pathWeight(graph, Arrays.asList(10,5,8,9,10,5,8,9,10)), delta);
    }

    @Test
    public void shortestPath() {
        MutableDoubleWeightedIntGraph graph = new DoubleWeightedIntAdjacencyList(11, true);
        Arrays.asList(edges1).forEach(a -> graph.addEdge(a[0], a[1], a[2]));

        assertArrayEquals(new int[]{0, 1}, Paths.shortestPath(graph, 0, 1));
        System.out.println(Arrays.toString(Paths.shortestPath(graph, 1, 10)));
        assertArrayEquals(new int[]{1,8,9,10}, Paths.shortestPath(graph, 1, 10));
    }
}