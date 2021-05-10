# Graphs and graph-based algorithms

In this series of code challenges we implement graphs and various algorithms on graphs and graph-based problems.

# Day 35: Graph implementations

We provide a number of implementations of graphs that will be used in future problems.

## Challenge

We're asked to provide an implementation of a weighted undirected graph with arbitrary vertex labels. I also provide
implementations of unweighted undirected graphs with successive integer vertex labels, and unweighted undirected graphs
with arbitrary vertex labels.

## Approach

### Undirected and unweighted with arbitrary vertex labels

The information is stored in a HashMap from vertices to a list of its neighbors. Adding a vertex to the graph means
adding an entry to the HashMap with the vertex label as the key and an empty list as the value. To add an edge `v1, v2`
to the graph, we get the adjacency lists for `v1` and `v2` from the hash map, and then add the other vertex to the end
of the list if it is not already in the list.

To get the neighbors of a vertex, we get the list from the HashMap and return it. The size of the graph is the size of
the map.

### Undirected and weighted with arbitrary vertex labels

We can generalize the above approach to a weighted graph by storing the neighbors of a vertex along with the weights of
the edge that connects the vertex with its neighbor. This involved creating a pair type:

```java
class VertexAndWeight<T, W> {
    private T vertex;
    private W weight;
}
```

Then instead of storing a list of neighboring vertices for each vertex, we store a list of neighboring vertices along
with the corresponding edge weights:

```java
Map<T, List<VertexAndWeight<T, W>>>
```

### Undirected graph with successive integer vertex labels

In many applications, vertex labels are not required to solve the specific graph-related problem, and vertices can be
labeled with successive integer indices. This permits a dramatically more time-efficient and space-efficient
representation of graphs, using plain Java arrays with primitive values, instead of costly HashMaps with generic values.
In problems where the vertex labels are important, the labels of each vertex can be stored in an array, and a hash map
from vertex labels to integer indices can be kept for lookup. Then graph-intensive algorithms can be performed with the
efficient integer representation with the ability to look up vertex labels or indices when needed.

In the basic approach, a two-dimensional array of ints is kept, which store adjacency lists of each vertex. Each vertex
is associated with an array index starting from 0. To add vertices, the array is resized and empty adjacency lists are
added into the new slots. To add an edge, indices are added to the end of the adjacency lists for the other vertex. To
allow adding to the end of the arrays, extra capacity is allocated in each array, and the number of actual neighbors in
the array is stored. the array is stored. When the number of neighbors exceeds the capacity, the array is copied into a
new larger array.

Other than the complexity involved with maintaining the dynamically sized arrays, this approach is simple and follows
the exact same approaches as adjacency list approach above.

### Undirected graph with successive integer vertex labels via an adjacency matrix

A graph implementation with successive integer labels is also provided with an adjacency matrix. This approach stores
an `n x n` two-dimensional array of booleans where the `[i][j]`th value indicates whether and edge exists between
vertex `j` and vertex `i`.

Adding vertices involves resizing the underlying array. Adding an edge involves setting a value in the array to true.
Returning a list of neighbors requires iterating through a row of the array and collecting the results into a result
array.

## Efficiency

The approaches from above have the following efficiencies:

| Representation | Add vertex | Add edge   | Get neighbors  | Check edge |  Space  |
| :------------- | :--------- | :--------- | :------------- | :--------- | :-------|
| Adjacency List | O(1)       | O(E)       | O(1)           | O(E)       | O(V + E)|
| Adjacency Matrix| O(V^2)    | O(1)       | O(V)           | O(1)       | O(V^2)  |

## API

The API for the various graph implementations are documented in the interfaces:

### Undirected unweighted with arbitrary vertex labels
```java
public interface Graph<T> {
    T addVertex(T vertex);

    void addEdge(T vertex1, T vertex2);

    List<T> neighbors(T vertex);

    Set<T> getVertices();

    int size();
}
```
### Undirected unweighted with successive integer vertex labels
```java
public interface IntGraph {
    int size();

    void resize(int size) throws Exception;

    void addEdge(int i, int j);

    int[] neighbors(int i);

    boolean neighbors(int i, int j);
}
```
### Undirected weighted with arbitrary vertex labels
```java

public interface WeightedGraph<T, W> {
    T addVertex(T vertex);

    void addEdge(T vertex1, T vertex2, W weight);

    @Nullable
    W getWeight(T vertex1, T vertex2);

    List<VertexAndWeight<T, W>> neighborsWithWeight(T vertex);

    List<T> neighbors(T vertex);

    Set<T> getVertices();

    int size();
}
```