package challenges.graph;

import com.google.common.base.Objects;

public class VertexAndWeight<T, W> {
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