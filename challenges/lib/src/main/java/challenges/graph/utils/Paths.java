package challenges.graph.utils;

import challenges.graph.interfaces.DoubleWeightedIntGraph;
import challenges.graph.interfaces.DoubleWeightedIntGraph.NeighborsAndWeights;
import challenges.graph.interfaces.WeightedGraph;
import challenges.graph.interfaces.WeightedGraph.VertexAndWeight;
import challenges.stacksQueues.IndexedDoublePriorityQueue;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class Paths {
    /**
     * Determines whether or not a sequence of vertices is a valid path through the graph, and if so, determines the
     * total sum of the weights for the graph.
     * @param graph A weighted graph
     * @param path An iterable of vertex labels
     * @param <T> The value associated with each vertex
     * @return null if the path doesn't exist, otherwise returns the sum of the weights along the path.
     */
    public static <T> Double pathWeight(WeightedGraph<T, Double> graph, Iterable<T> path) {
        Iterator<T> it = path.iterator();
        T vertex = it.next();
        Double sum = null;
        while(it.hasNext()) {
            T next = it.next();
            List<VertexAndWeight<T,Double>> vws = graph.neighborsWithWeight(vertex);
            VertexAndWeight<T,Double> vertexWeight = vws.stream()
                    .filter(vw -> vw.getVertex().equals(next))
                    .findAny()
                    .orElse(null);
            if (vertexWeight == null) return null;
            double weight = vertexWeight.getWeight();
            sum = sum == null ? weight : sum + weight;
        }
        return sum;
    }
}
