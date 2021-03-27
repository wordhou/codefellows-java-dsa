# Singly Linked List

We implement a LinkedList class in our linkedList package. Our linked list needs to support just three operations: `insert`, which adds an element to the beginning of the list, `includes`, which returns whether or not an element is contained in the linked list, and `toString`, which formats a linked list into a string.

## Challenge

The `LinkedList<T>` class consists of a single property, `head`, which points to a `Node<T>` object. The `Node<T>` object contains a value of type `T` and a (possibly null) reference to another `Node<T>` object. Constructing a new LinkedList just creates a new LinkedList object with the `head` property initialized to `null`.

Adding elements to the linked list involves creating a new node with the new value which points to the current value of `head`.

Then, the includes method checks to see whether or not an item is in the list. This is implemented using a simple tail recursive approach. If the current node's item is the item we're looking for, return `true`, and if the next node is `null` (meaning we've reached the end of the list), return `false.` Otherwise, we repeat this check for the next item in the list.

Finally, `toString` was implemented recursively as well.

## Approach & Efficiency

The efficiency of insertion is `O(1)` since it just requires initializing a node and changing a pointer.

The efficiency of the search method `.includes` is `O(n)` in the number of elements, since we are constrained to a linear search by the data structure.

The efficiency of the `toString` method is also `O(n)` since we have to go through the entire list to display every element.

## API

```
LinkedList() // constructor takes no arguments

void insert(T item) // Inserts an item into the start of the list

boolean isEmpty() // Returns whether or not the list is empty

boolean includes(T item) // Returns whether or not the item is contained in the list

String toString() // Formats a list in a string format "{ 1 } -> { 2 } -> { 3 } -> NULL".
```
