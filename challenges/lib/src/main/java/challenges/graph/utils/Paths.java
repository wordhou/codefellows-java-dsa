package challenges.graph.utils;

import challenges.graph.interfaces.DoubleWeightedIntGraph;
import challenges.graph.interfaces.DoubleWeightedIntGraph.NeighborsAndWeights;
import challenges.graph.interfaces.MutableWeightedGraph;
import challenges.graph.interfaces.WeightedGraph;
import challenges.stacksQueues.IndexedDoublePriorityQueue;
import challenges.stacksQueues.IntDynamicArray;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;

public class Paths {
    /**
     * Determines whether or not a sequence of vertices is a valid path through the graph, and if so, determines the
     * total sum of the weights for the graph.
     *
     * @param graph A weighted graph
     * @param path  An iterable of vertex labels
     * @param <T>   The value associated with each vertex
     * @return null if the path doesn't exist, otherwise returns the sum of the weights along the path.
     */
    public static <T> Double pathWeight(WeightedGraph<T, Double> graph, Iterable<T> path) {
        Iterator<T> it = path.iterator();
        T vertex = it.next();
        Double sum = null;
        while (it.hasNext()) {
            T next = it.next();
            if (!graph.neighbors(vertex, next)) return null;
            double weight = graph.getWeight(vertex, next);
            sum = sum == null ? weight : sum + weight;
            vertex = next;
        }
        return sum;
    }

    /**
     * Finds a shortest path from the start node to the finish node using Djikstra's algorithm.
     *
     * @param graph  a double weighted graph on int nodes
     * @param start  the starting node
     * @param finish the ending node
     * @return an int array containing the nodes along the path from start to finish, inclusive.
     */
    public static int[] shortestPath(DoubleWeightedIntGraph graph, int start, int finish) {
        IndexedDoublePriorityQueue queue = new IndexedDoublePriorityQueue(graph.size());
        BitSet visited = new BitSet(graph.size());
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);
        queue.add(start, 0);

        int vertex = -1;

        while (!queue.isEmpty()) {
            double weight = queue.findMinWeight();
            vertex = queue.removeMin();
            visited.set(vertex);

            if (vertex == finish) break;

            NeighborsAndWeights neighborsAndWeights = graph.neighborsWithWeight(vertex);
            int[] nbrs = neighborsAndWeights.getNeighbors();
            double[] wgts = neighborsAndWeights.getWeights();

            for (int i = 0; i < neighborsAndWeights.size(); i++) {
                if (!visited.get(nbrs[i])) {
                    parent[nbrs[i]] = vertex;
                    queue.put(nbrs[i], wgts[i] + weight);
                }
            }
        }

        if (vertex != finish) return new int[0];

        IntDynamicArray array = new IntDynamicArray();
        while (vertex != start) {
            array.push(vertex);
            vertex = parent[vertex];
        }
        array.push(vertex);

        return array.reversed();
    }
}