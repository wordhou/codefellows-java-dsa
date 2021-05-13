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

# Day 36: Traversals

We implement the classic traversals on undirected graphs: breadth first, and depth first.

## Challenge

Implement a method that traverses a graph from a given starting node, visiting every done reachable from the starting
node exactly once.

## Approach

The approach is the same as traversing a tree, with one small modification. In a graph there can be cycles, paths that
lead back to an already visited vertex. We need to keep track of the vertices that have been visited, otherwise the
breadth first and depth first traversals wouldn't terminate. So we maintain a set of vertices that have been visited,
and then when we add another node to the queue (or the stack, for DFS), we first check to see if it has already been
visited. If it hasn't been visited, we add it to our visited set and if it has, we skip that vertex and move on to the
next.

## Efficiency

The efficiency of a traversal depends on the specifics of the graph implementation used. When the graph is implemented
with an adjacency list, or some other implementation with an O(1) retrieval of the list of neighbors, the algorithm runs
in O(V + E) time, where V is the number of vertices in the graph, and E is the number of edges in the graph. This is
because we visit every vertex at most once. For each vertex, we perform a constant time operation on vertex (adding it
to the visited set and consuming it), and then iterate through each of its neighbors. For each neighbor, we perform
another set of constant time operations (a set contains check and an enqueue operation). The total number of neighbors
of each vertex is the same as the number of edges in the graph, so the running time will be O(V + E). This algorithm
also uses O(V) space, since the stack/queue can hold at most V elements (since no vertex gets added twice), and the set
will hold at most V vertices as well.

## Solution

The solution code can be found in
the [Traversals](../challenges/lib/src/main/java/challenges/graph/utils/Traversals.java)
class. These traversals run on any class that implements
the [Traversable<T>](../challenges/lib/src/main/java/challenges/graph/interfaces/Traversable.java) interface:

```java
public interface Traversable<T> {
    Collection<T> neighbors(T vertex);

    boolean contains(T vertex);
}
```

This includes my implementations of unweighted and weighted graphs as well as undirected and directed graphs. (The
directed graph implementations have not been written yet.)

# Day 37: Paths

The challenge today asks us to implement a method that verifies whether a sequence of nodes is a path in a graph, and
also determining the total weight along that path if it exists.

## Challenge

We are given a weighted graph and a sequence of vertices from that graph. We're asked to determine if that sequence
forms a path in the graph. If so, we return the numeric value of the sum of all the weights in that path. Otherwise, we
return null. The double is wrapped as a Java Object (Double) so that we can return null to indicate that the sequence of
nodes is not a path.

## Approach

The approach is straight forward. Initialize the sum to 0. Iterate through the sequence. For each vertex in the
sequence, check whether the next vertex belongs to the neighbors of the current vertex. If so, add the weight of that
edge to the current sum. If not, return null.

## Efficiency

On a graph represented as an adjacency list, this algorithm runs in O(NE) time, where E is the number of edges in the
set, and N is the number of vertices in the given sequence, since for each vertex in the sequence, we get its
neighbors (an O(1) operation) and iterate through them to check whether the next item is in the set. On a graph
represented by an adjacency matrix, the algorithm runs in O(N) time, since checking whether an edge exists and querying
its weight is an O(1) operation.

## Solution

```java
/**
 * Determines whether or not a sequence of vertices is a valid path through the graph, and if so, determines the
 * total sum of the weights for the graph.
 *
 * @param graph A weighted graph
 * @param path  An iterable of vertex labels
 * @param <T>   The type of the value associated with each vertex
 * @return null if the path doesn't exist, otherwise returns the sum of the weights along the path.
 */
public static<T> Double pathWeight(WeightedGraph<T, Double> graph,Iterable<T> path){
        Iterator<T> it=path.iterator();
        T vertex=it.next();
        Double sum=null;

        while(it.hasNext()){
        T next=it.next();
        if(!graph.neighbors(vertex,next))return null;

        double weight=graph.getWeight(vertex,next);
        sum=sum==null?weight:sum+weight;

        vertex=next;
        }

        return sum;
        }
```

# Day 38: More traversals

The challenge asks us to implement a depth-first (pre-order) traversal on a graph.

## Challenge

Implement a traversal of a graph that processes nodes in depth-first order. This algorithm works on both undirected and
directed graphs, and only requires the ability to get the neighbors of a node. We will write this algorithm to work on
our Traversable class.

## Approach

The approach is identical to the depth-first search on binary trees using an explicit stack. The only implementation
difference is that we track the visited nodes, and add a node to the visited set whenever we enqueue it.

## Efficiency

Traversing through a graph depends on the efficiency of getting the list of neighbors. In an adjacency list, this is an
O(1) operation.

For each vertex, we iterate through its neighbors and perform a number of constant time operations. In fact the sum of
the number of neighbors of each vertex is just the number of edges in the graph. Since we also need to perform a number
of constant time operations on each vertex, our total running time is O(V + E).

## Solution

The solution can be found in the [Traversals](../challenges/lib/src/main/java/challenges/graph/utils/Traversals.java)
class.

# Day 38 Extra: Topological Sort

Topological sorting takes a directed graph with no cycles (a directed acyclic graph, or DAG) and sorts the vertices in
an order where every vertex `x` comes before another vertex `y` if there exists a directed path from `x` to `y`.

Topological sorting algorithms also determine whether a graph has a cycle, since a graph with cycles cannot be
topologically sorted.

A classic application of the topological sort is building a project that consists of a sequence of jobs ordered on their
dependencies.

## Approach

A classic algorithm from Cormen et al., and originally written about by Karjan topologically sorts a graph using depth first searches on the nodes.

We start by populating a remaining vertex set with vertices to be sorted, and also populating an empty vertex set of
vertices that have been visited during a depth first search. We start a depth first search at any vertex in the
remaining vertex set. If at any point in the depth first search we reach a node that has already been visited during
that depth first search, we end the algorithm, since we found a cycle. After we finish visiting a vertex and all its
children, we prepend it to the result list and remove it from the vertex set. This is essentially a depth-first post
order. Once the depth first search terminates, we remove every node that we visited from the graph, and remove those
vertices from the remaining vertex set. If there are still vertices in the graph after this depth first search, we pick
another vertex still in the vertex set, and start a depth first search there. If at any point during the depth first
search we reach a node that is no longer in the vertex set, we terminate that depth first search. This repeats until
there are no more vertices in the remaining vertex set.

## Efficiency

Every vertex is visited exactly once during this algorithm. For each vertex, we iterate over its neighbors and check
whether they're in the remaining vertex set, or whether they're in the visited set. Then for each vertex we will add and
remove it from the visited set once and remove it from the remaining vertex set once. Since all of these operations are
O(1), our time complexity is O(V + E), the same as for a depth first traversal.