# Hash-based data structures

Hash tables describe a way of storing data based on hash functions that exhibit efficient add, update, lookup, and
delete operations. In this series of challenges we implement hash-based data structures and use them to solve various
solutions.

# Class 30: Hash Table Implementation

We implement a hash map using separate chaining (using a linked list to store nodes with the same hash index). The
HashMap implements a map, which accepts values of a certain type associated with keys of another type. Each key is
associated with a value and the value for a given key can be added, updated, or removed. The hash map implements a map
by storing key-value pairs in an indexed array, and using a hash function to determine the array index for a given key.

## The Challenge

In the `Map` interface I define the API of the data structure and in the `HashTableSeparateChaining` class I define the
implementation. The type of hash map I implemented is called a hash map with separate chaining, which uses a linked list
at each array index to store the key-value pairs that are associated with that index.

## Approach

My `HashTableSeparateChaining<K, V>` class stores an array of `Object`, since Java doesn't support arrays of a specified
generic type. The array stores values of type `EntryNode<K,V>`, which has three properties: a `key` of type `K`,
a `value` of type `V`, and a `next` reference to the next node in the linked list. This way I didn't have to perform any
initialization, since the default `null` value of an Object array represents a hash index without any values.
Implementing the `add`, `contains`, `remove` and `get` methods just used the basic logic of singly-linked lists, which
we've covered before in detail. The hash index was chosen by taking the `.hashCode()` value of a key modulo the size of
the array.

I also chose to implement a hash table that scaled up and scaled down the size of its underlying array when the number
of entries stored changes. This is similar to the way a dynamic array is implemented. When the number of entries reaches
a certain proportion of the capacity of the underlying array, a new larger array is allocated and the entries from the
first array are rehashed and inserted into the larger array.

## Efficiency

A Hash Table has constant time or near-constant time operations for insertion, update, lookup, and deletion. This is
because it uses hashing and array indexing to find its elements, which are both O(1) operations. However, since the
array actually holds pointers to the head of linked lists, the operations can take O(m) where `m` is the length of the
linked list at an index. Thus care needs to be taken that the number of entries in a hash map doesn't exceed the
capacity of the array by a large factor, since this will guarantee that each linked list has multiple nodes.

To address this issue, we need to ensure that the load factor (the ratio between the number of entries and the size of
the array) doesn't exceed a certain value, and when it does, we allocate a new array, iterate through the original, and
rehash the entries into the new array. Although this resizing is an O(n) operation, as long as the size of the array is
being increased by a constant factor and the threshold for increasing the size of the array is a constant proportion of
the capacity, the running time for the operations is an amortized O(1). This is similar to resizing a dynamic array.

The choice of factors for the thresholds and the expansion and contractions is a tradeoff between space and time, where
choosing larger factors results in faster operations but more wasted space. I choose values for the thresholds at which
resizing happens and the factors that the array is expanded or contracted by:

```java
// When the load factor exceeds the expansion threshold, the hash table is expanded
static final double EXPANSION_THRESHOLD=0.8;
// The proportion that the size of the underlying buckets array is increased by during expansion
static final double EXPANSION_FACTOR=1.5;
// When the load factor drops below the expansion threshold, the hash table is contracted
static final double CONTRACTION_THRESHOLD=0.2;
// An factor that the size hash table is decreased by during contraction;
static final double CONTRACTION_FACTOR=0.67;
// The default capacity of the hash table if no initial capacity is given
static final int DEFAULT_CAPACITY=11;
```

## API

The API is documented in the [`Map` interface file](../challenges/lib/src/main/java/challenges/hashTable/Map.java). The
implementation can be found in
the [`HashTableSeparateChaining` class](../challenges/lib/src/main/java/challenges/hashTable/HashTableSeparateChaining.java)
.