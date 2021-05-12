package challenges.graph.interfaces;

import com.google.common.base.Objects;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class WeightedGraph<T, W> extends Traversable<T> {
    public abstract T addVertex(T vertex);

    public abstract void addEdge(T vertex1, T vertex2, W weight);

    @Nullable
    public abstract W getWeight(T vertex1, T vertex2);

    public abstract List<VertexAndWeight<T, W>> neighborsWithWeight(T vertex);

    public abstract List<T> neighbors(T vertex);

    public abstract boolean neighbors(T first, T second);

    public abstract Set<T> getVertices();

    public abstract int size();

    public abstract boolean isDirected();

    public static class VertexAndWeight<T, W> {
        private T vertex;
        private W weight;

        public VertexAndWeight(T vertex, W weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public T getVertex() {
            return vertex;
        }

        public W getWeight() {
            return weight;
        }

        public void setWeight(W weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VertexAndWeight<?, ?> that = (VertexAndWeight<?, ?>) o;
            return Objects.equal(vertex, that.vertex) && Objects.equal(weight, that.weight);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(vertex, weight);
        }
    }
}
