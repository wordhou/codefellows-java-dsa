# Stacks and Queues implementations

In this lab we define the interface for a basic stack and queue abstract data type and write various implementations. I've written more about the stack and queue [abstract data type](https://wordhou.github.io/reading-notes/adts.html) in my reading notes.

# Lab 10: Linked list based implementations

We're asked to implement the stack and queue types using linked lists. I defined interfaces for both the queue and the stack and them implemented them in the [`LinkedListQueue<T>`](../challenges/lib/src/main/java/challenges/stacksQueues/LinkedListQueue.java) and the [`LinkedListStack<T>`](../challenges/lib/src/main/java/challenges/stacksQueues/LinkedListStack.java) classes.

Interfaces are also defined for the [Queue](../challenges/lib/src/main/java/challenges/stacksQueues/Queue.java) and the [Stack](../challenges/lib/src/main/java/challenges/stacksQueues/Stack.java).

## Approach and Efficiency

No whiteboard is done for today's lab since I've already implemented the singly linked list in detail in previous labs.

The one consideration to make is which linked-list operations to use to implement the stack and queue operations. For the stack, since we have O(1) operations at the head of the list, we push and pop by adding and removing elements from the head of the list. For the queue, we want to add on one end of the list and remove from the other end. Since we have O(n) removal from the tail of the list but O(1) addition to the tail, it's natural to add elements to the tail of the list and remove them from the head so that both of our operations are constant time.

## Extra

I also implemented a fixed-sized circular buffer using an array. This models a queue with a fixed capacity that rejects additions after it's reached capacity. The interface for this is defined in [BoundedQueue<T>](../challenges/lib/src/main/java/challenges/stacksQueues/BoundedQueue.java) and the implementation is defined in [CircularBuffer<T>](../challenges/lib/src/main/java/challenges/stacksQueues/CircularBuffer.java).
