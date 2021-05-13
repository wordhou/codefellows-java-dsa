package challenges.graph;

import challenges.graph.impl.WeightedAdjacencyList;
import challenges.graph.interfaces.MutableWeightedClass;

public class WeightedAdjacencyListTest extends MutableWeightedClassTest {
    @Override
    protected MutableWeightedClass<Integer, Integer> createInstance() {
        return new WeightedAdjacencyList<>();
    }
}
