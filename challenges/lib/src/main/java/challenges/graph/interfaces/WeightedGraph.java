package challenges.graph.interfaces;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class WeightedGraph<T, W> extends Graph<T> {
    @Nullable
    public abstract W getWeight(T vertex1, T vertex2);

    public abstract List<MutableWeightedGraph.VertexAndWeight<T, W>> neighborsWithWeight(T vertex);

    public abstract boolean neighbors(T first, T second);

    public abstract int size();

    public abstract boolean isDirected();

}
