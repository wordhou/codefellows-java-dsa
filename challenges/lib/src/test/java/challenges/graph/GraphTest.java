package challenges.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public abstract class GraphTest {
    Graph<Integer> graph;

    protected abstract Graph<Integer> createInstance();

    @Before
    public void init() {
        graph = createInstance();
    }

    @Test
    public void sizeOfVertices() {
        assertEquals(0, graph.size());
        graph.addVertex(5);
        assertEquals(1, graph.size());
        graph.addVertex(5);
        assertEquals("Adding same vertex again doesn't increase size of graph", 1, graph.size());
        graph.addVertex(1);
        graph.addVertex(2);
        assertEquals(3, graph.size());
        graph.addEdge(1, 2);
        assertEquals("Adding edges leaves size unchanged", 3, graph.size());
    }

    @Test
    public void addEdgeWithVertexNotInGraph() {
        graph.addVertex(5);
        graph.addVertex(6);
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> graph.addEdge(1, 2));
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> graph.addEdge(4, 5));
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> graph.addEdge(5, 4));
        graph.addEdge(5, 6);
    }

    @Test
    public void addingEdgeTwiceDoesNotAddItTwice() {
        Arrays.asList(1,2,3).forEach(graph::addVertex);
        int[][] edges = {
                {1, 2}, {1, 3}, {1, 2}, {1, 3},
                {2, 3}, {2, 3}, {2, 3}, {2, 3}
        };

        Arrays.asList(edges).forEach( e -> graph.addEdge(e[0], e[1]));
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(1).size());
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(2).size());
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(3).size());
    }


    @Test
    public void getNeighbors() {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(graph::addVertex);
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
                0, graph.neighbors(1).size());
        Arrays.asList(edges).forEach( e -> graph.addEdge(e[0], e[1]));
        assertCollectionsHaveSameElements(Arrays.asList(2, 3, 5, 7), graph.neighbors(1));
        assertCollectionsHaveSameElements(Arrays.asList(2,7), graph.neighbors(6));
        assertCollectionsHaveSameElements(Arrays.asList(8,9), graph.neighbors(10));
        assertCollectionsHaveSameElements(Arrays.asList(1,3,6,7), graph.neighbors(2));
        assertCollectionsHaveSameElements(Arrays.asList(1,2,5,6,9), graph.neighbors(7));
    }

    static <T> void assertCollectionsHaveSameElements(Collection<T> expected, Collection<T> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }
}
