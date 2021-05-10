package challenges.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static challenges.utils.Assertions.assertCollectionsHaveSameElements;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

abstract public class WeightedGraphTest {
    WeightedGraph<Integer, Integer> graph;

    static int[][] example1 = {
            {1, 2, 2}, {1, 3, 3}, {1, 2, 1}, {1, 3, 4},
            {2, 3, 1}, {2, 3, 2}, {2, 3, 4}, {2, 3, 5}
    };

    static int[][] example2 = {
            {1, 2, 1}, {1, 3, 2}, {1, 5, 3}, {1, 7, 4},
            {2, 3, 5}, {2, 6, 6}, {2, 7, 7},
            {3, 4, 8},
            {4, 8, 9},
            {5, 7, 10}, {5, 9, 11},
            {6, 7, 12},
            {7, 9, 13},
            {8, 10, 14},
            {9, 10, 15},
    };

    protected abstract WeightedGraph<Integer, Integer> createInstance();

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
        graph.addEdge(1, 2, 1);
        assertEquals("Adding edges leaves size unchanged", 3, graph.size());
    }

    @Test
    public void addEdgeWithVertexNotInGraph() {
        graph.addVertex(5);
        graph.addVertex(6);
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> graph.addEdge(1, 2, 1));
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> graph.addEdge(4, 5, 1));
        assertThrows("adding edge with vertex not in graph",
                NoSuchElementException.class, () -> graph.addEdge(5, 4, 1));
        graph.addEdge(5, 6, 1);
    }

    @Test
    public void addingEdgesMultipleTimes() {
        Arrays.asList(1,2,3).forEach(graph::addVertex);
        Arrays.asList(example1).forEach( e -> graph.addEdge(e[0], e[1], 1));
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(1).size());
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(2).size());
        assertEquals("Adding an edge multiple times", 2, graph.neighbors(3).size());
    }

    @Test
    public void getWeight() {
        Arrays.asList(1,2,3).forEach(graph::addVertex);
        Arrays.asList(example1).forEach( e -> graph.addEdge(e[0], e[1], e[2]));
        assertEquals(1, (int) graph.getWeight(1, 2));
        assertEquals(1, (int) graph.getWeight(2, 1));
        assertEquals(4, (int) graph.getWeight(1, 3));
        assertEquals(4, (int) graph.getWeight(3, 1));
        assertEquals(5, (int) graph.getWeight(2, 3));
        assertEquals(5, (int) graph.getWeight(3, 2));
    }


    @Test
    public void getNeighbors() {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(graph::addVertex);
        assertEquals("neighbors of vertex with no neighbors is empty",
                0, graph.neighbors(1).size());
        Arrays.asList(example2).forEach( e -> graph.addEdge(e[0], e[1], e[2]));
        assertCollectionsHaveSameElements(Arrays.asList(2, 3, 5, 7), graph.neighbors(1));
        assertCollectionsHaveSameElements(Arrays.asList(2,7), graph.neighbors(6));
        assertCollectionsHaveSameElements(Arrays.asList(8,9), graph.neighbors(10));
        assertCollectionsHaveSameElements(Arrays.asList(1,3,6,7), graph.neighbors(2));
        assertCollectionsHaveSameElements(Arrays.asList(1,2,5,6,9), graph.neighbors(7));
    }

    @Test
    public void getNeighborsWithWeight() {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(graph::addVertex);
        assertEquals("neighbors of vertex with no neighbors is empty",
                0, graph.neighbors(1).size());
        Arrays.asList(example2).forEach( e -> graph.addEdge(e[0], e[1], e[2]));

        List<VertexAndWeight<Integer, Integer>> expected1 = Arrays.asList(
                new VertexAndWeight<>(2, 1),
                new VertexAndWeight<>(3, 2),
                new VertexAndWeight<>(5, 3),
                new VertexAndWeight<>(7, 4)
        );
        List<VertexAndWeight<Integer, Integer>> expected2 = Arrays.asList(
                new VertexAndWeight<>(1, 1),
                new VertexAndWeight<>(3, 5),
                new VertexAndWeight<>(6, 6),
                new VertexAndWeight<>(7, 7)
        );
        List<VertexAndWeight<Integer, Integer>> expected7 = Arrays.asList(
                new VertexAndWeight<>(1, 4),
                new VertexAndWeight<>(2, 7),
                new VertexAndWeight<>(5, 10),
                new VertexAndWeight<>(6, 12),
                new VertexAndWeight<>(9, 13)
        );
        assertCollectionsHaveSameElements(expected1, graph.neighborsWithWeight(1));
        assertCollectionsHaveSameElements(expected2, graph.neighborsWithWeight(2));
        assertCollectionsHaveSameElements(expected7, graph.neighborsWithWeight(7));
    }
}
