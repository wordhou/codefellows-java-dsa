package challenges.graph.interfaces;

import challenges.graph.impl.AdjacencyList;
import challenges.graph.impl.IntAdjacencyList;
import challenges.graph.utils.Traversals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static challenges.utils.Assertions.assertCollectionsHaveSameElements;
import static org.junit.Assert.*;

public class GraphTest {
    MutableGraph<Integer> graph1;
    MutableGraph<Integer> graph2;

    static int[][] edges1 = {
            {1, 2}, {1, 3}, {1, 2}, {1, 3},
            {2, 3}, {2, 3}, {2, 3}, {2, 3}
    };
    static int[][] edges2 = {
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

    static <T> Collection<T> breadthFirstEnumerate(Graph<T> graph, T init) {
        Collection<T> result = new ArrayList<>();
        Traversals.breadthFirstTraversal(graph, init, result::add);
        return result;
    }

    static <T> Collection<T> depthFirstEnumerate(Graph<T> graph, T init) {
        Collection<T> result = new ArrayList<>();
        Traversals.depthFirstTraversal(graph, init, result::add);
        return result;
    }

    public void initUndirected() {
        graph1 = new AdjacencyList<>(false);
        graph2 = new AdjacencyList<>(false);

        Arrays.asList(1, 2, 3).forEach(graph1::addVertex);
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(graph2::addVertex);

        Arrays.asList(edges1).forEach( a -> graph1.addEdge(a[0], a[1]) );
        Arrays.asList(edges2).forEach( a -> graph2.addEdge(a[0], a[1]) );
    }

    @Test
    public void breadthFirstTraversal() {
        initUndirected();
        assertCollectionsHaveSameElements(Arrays.asList(1, 2, 3),
                breadthFirstEnumerate(graph1, 1));
        assertCollectionsHaveSameElements(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                breadthFirstEnumerate(graph2, 1));
    }

    @Test
    public void depthFirstTraversal() {
        assertCollectionsHaveSameElements(Arrays.asList(1, 2, 3),
                depthFirstEnumerate(graph1, 1));
        assertCollectionsHaveSameElements(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                depthFirstEnumerate(graph2, 1));
    }

    @Test
    public void connected() {
    }
}