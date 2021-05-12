package challenges.graph;

import challenges.graph.interfaces.MutableGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static challenges.utils.Assertions.assertCollectionsHaveSameElements;
import static org.junit.Assert.*;

public abstract class MutableGraphTest {
    MutableGraph<Integer> mutableGraph;

    protected abstract MutableGraph<Integer> createInstance();

    @Before
    public void init() {
        mutableGraph = createInstance();
    }

    @Test
    public void sizeOfVertices() {
        assertEquals(0, mutableGraph.size());
        mutableGraph.addVertex(5);
        assertEquals(1, mutableGraph.size());
        mutableGraph.addVertex(5);
        assertEquals("Adding same vertex again doesn't increase size of graph", 1, mutableGraph.size());
        mutableGraph.addVertex(1);
        mutableGraph.addVertex(2);
        assertEquals(3, mutableGraph.size());
        mutableGraph.addEdge(1, 2);
        assertEquals("Adding edges leaves size unchanged", 3, mutableGraph.size());
    }

    @Test
    public void addEdgeWithVertexNotInGraph() {
        mutableGraph.addVertex(5);
        mutableGraph.addVertex(6);
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> mutableGraph.addEdge(1, 2));
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> mutableGraph.addEdge(4, 5));
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> mutableGraph.addEdge(5, 4));
        mutableGraph.addEdge(5, 6);
    }

    @Test
    public void addingEdgeTwiceDoesNotAddItTwice() {
        Arrays.asList(1,2,3).forEach(mutableGraph::addVertex);
        int[][] edges = {
                {1, 2}, {1, 3}, {1, 2}, {1, 3},
                {2, 3}, {2, 3}, {2, 3}, {2, 3}
        };

        Arrays.asList(edges).forEach( e -> mutableGraph.addEdge(e[0], e[1]));
        assertEquals("Adding an edge multiple times", 2, mutableGraph.neighbors(1).size());
        assertEquals("Adding an edge multiple times", 2, mutableGraph.neighbors(2).size());
        assertEquals("Adding an edge multiple times", 2, mutableGraph.neighbors(3).size());
    }


    @Test
    public void getNeighbors() {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(mutableGraph::addVertex);
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
                0, mutableGraph.neighbors(1).size());
        Arrays.asList(edges).forEach( e -> mutableGraph.addEdge(e[0], e[1]));
        assertCollectionsHaveSameElements(Arrays.asList(2, 3, 5, 7), mutableGraph.neighbors(1));
        assertCollectionsHaveSameElements(Arrays.asList(2,7), mutableGraph.neighbors(6));
        assertCollectionsHaveSameElements(Arrays.asList(8,9), mutableGraph.neighbors(10));
        assertCollectionsHaveSameElements(Arrays.asList(1,3,6,7), mutableGraph.neighbors(2));
        assertCollectionsHaveSameElements(Arrays.asList(1,2,5,6,9), mutableGraph.neighbors(7));
    }

}
