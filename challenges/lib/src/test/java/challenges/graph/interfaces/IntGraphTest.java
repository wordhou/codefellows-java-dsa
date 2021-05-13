package challenges.graph.interfaces;

import challenges.graph.impl.IntAdjacencyList;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class IntGraphTest {
    final static int[][][] dags = {
            {{0, 1}},
            {{0, 1}, {1, 2}, {2, 3}},
            {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}},
            {{0, 2}, {0, 4}, {1, 3}, {1, 5}, {2, 3}, {4, 6}},
            {
                    {0, 1}, {0, 3}, {0, 5},
                    {1, 2}, {1, 4}, {1, 6},
                    {7, 8}, {7, 9}, {7, 10},
                    {8, 10}, {8, 11}, {8, 13},
                    {9, 11}, {9, 13},
                    {12, 13}
            },
            {
                    {0, 2}, {0, 1}, {0, 4}, {0, 7},
                    {1, 2}, {1, 3}, {1, 5}, {1, 7},
                    {2, 3}, {2, 3}, {2, 4},
                    {3, 4},
                    {4, 8},
                    {5, 7}, {5, 9},
                    {6, 7},
                    {7, 9},
                    {8, 10},
                    {9, 10},
            },
    };

    final static int[][][] cyclicGraphs = {
            {{1, 2}, {2, 1}},
            {{0, 2}, {2, 1}, {1, 0}},
            {{0, 4}, {4, 2}, {2, 3}, {3, 4}, {4, 1}, {0, 2}, {1, 2}},
            {
                    {0, 2}, {0, 1}, {0, 4}, {0, 7},
                    {1, 2}, {1, 3}, {1, 5}, {1, 7},
                    {2, 3}, {2, 3}, {2, 4},
                    {3, 4},
                    {4, 8},
                    {5, 7}, {5, 9},
                    {6, 7},
                    {7, 9},
                    {8, 10},
                    {9, 10},
                    {10, 4},
            },
    };

    @Test
    public void topologicalSortUndirected() {
        for (int[][] dag : dags) {
            MutableIntGraph graph = new IntAdjacencyList(maxOfMax(dag) + 1, false);
            for (int[] edge : dag) graph.addEdge(edge[0], edge[1]);

            assertNull(graph.topologicalSort());
        }
    }

    @Test
    public void topologicalSortDags() {
        for (int[][] dag : dags) {
            MutableIntGraph graph = new IntAdjacencyList(maxOfMax(dag) + 1, true);
            for (int[] edge : dag) graph.addEdge(edge[0], edge[1]);

            int[] sorted = graph.topologicalSort();

            for (int[] edge : dag) {
                assertTrue(occursInOrder(sorted, edge[0], edge[1]));
            }
        }
    }

    @Test
    public void topologicalSortCyclicDirectedGraphs() {
        for (int[][] edges : cyclicGraphs) {
            MutableIntGraph graph = new IntAdjacencyList(maxOfMax(edges) + 1, true);
            for (int[] edge : edges) graph.addEdge(edge[0], edge[1]);

            assertNull(graph.topologicalSort());
        }
    }

    protected boolean occursInOrder(int[] array, int first, int second) {
        for (int i : array) {
            if (i == first) return true;
            if (i == second) return false;
        }
        throw new NoSuchElementException("Neither element exists in array");
    }

    protected int maxOfMax(int[][] array) {
        int max = 0;
        for (int[] ints : array) {
            for (int i : ints) {
                if (i > max) max = i;
            }
        }
        return max;
    }
}