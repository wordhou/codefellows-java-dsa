package challenges.graph;

import challenges.graph.interfaces.MutableIntGraph;
import org.junit.Test;

import java.util.Arrays;

import static challenges.utils.Assertions.assertArraysHaveSameElements;
import static org.junit.Assert.*;

public abstract class DirectedMutableIntGraphTest {
    private MutableIntGraph graph;

    protected abstract MutableIntGraph createInstance(int size);

    @Test
    public void sizeOfVertices() {
        Arrays.asList(0,1,2,3,4,5,7,9).forEach(
                i -> assertEquals((int) i, createInstance(i).size()));

        graph = createInstance(10);
        assertEquals(10, graph.size());

        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        assertEquals("Adding edges leaves size unchanged", 10, graph.size());
    }

    @Test
    public void addEdgeWithVertexNotInGraph() {
        graph = createInstance(5);
        graph.addEdge(3, 4);
        assertThrows("adding edge with vertex not in graph",
                ArrayIndexOutOfBoundsException.class, () -> graph.addEdge(4, 5));
        assertThrows("adding edge with vertex not in graph",
                ArrayIndexOutOfBoundsException.class, () -> graph.addEdge(5, 4));
        assertThrows("adding edge with vertex not in graph",
                ArrayIndexOutOfBoundsException.class, () -> graph.addEdge(5, 6));
    }

    @Test
    public void addingEdgeTwiceDoesNotAddItTwice() {
        graph = createInstance(4);
        int[][] edges = {
                {1, 2}, {1, 3}, {2, 1}, {1, 3},
                {2, 3}, {2, 3}, {2, 3}, {2, 3}
        };

        Arrays.asList(edges).forEach( e -> graph.addEdge(e[0], e[1]));
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(1).length);
        assertTrue(graph.neighbors(1, 2));
        assertTrue(graph.neighbors(2, 1));
        assertTrue(graph.neighbors(1, 3));
        assertFalse(graph.neighbors(3, 1));
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(2).length);
        assertEquals("Adding an edge multiple times", 0, graph.neighbors(3).length);
    }


    @Test
    public void getNeighbors() {
        graph = createInstance(11);
        int[][] edges = {
                {1, 2}, {1, 3}, {1, 5}, {1, 7},
                {2, 3}, {2, 6}, {2, 7},
                {3, 4},
                {4, 8},
                {5, 7}, {5, 9},
                {6, 7},
                {7, 9},
                {8, 10},
                {9, 10},
        };
        assertEquals("neighbors of vertex with no neighbors is empty",
                0, graph.neighbors(1).length);
        Arrays.asList(edges).forEach( e -> graph.addEdge(e[0], e[1]));
        assertArraysHaveSameElements(new int[]{2, 3, 5, 7}, graph.neighbors(1));
        assertArraysHaveSameElements(new int[]{2, 7}, graph.neighbors(6));
        assertArraysHaveSameElements(new int[]{8, 9}, graph.neighbors(10));
        assertArraysHaveSameElements(new int[]{1,3,6,7}, graph.neighbors(2));
        assertArraysHaveSameElements(new int[]{1,2,5,6,9}, graph.neighbors(7));
    }

    public void resize() {
        // TODO test resizing of graph
    }
}
