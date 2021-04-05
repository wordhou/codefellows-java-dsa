package challenges.stacksQueues;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TwoStacksQueueTest {

@Test
public void isEmpty() {
    Queue<Integer> l = new TwoStacksQueue<Integer, LinkedListStack<Integer>>(LinkedListStack<Integer>::new);
    assertTrue("new queue is empty", l.isEmpty());
    l.enqueue(5);
    l.dequeue();
    assertTrue("isEmpty circular buffer", l.isEmpty());
}

@Test
public void peek() {
    Queue<Integer> l = new TwoStacksQueue<Integer, LinkedListStack<Integer>>(LinkedListStack<Integer>::new);
    assertNull("peek is null on empty list", l.peek());
    l.enqueue(5);
    assertEquals("peek gets element at front when it exists", 5, (int) l.peek());
    l.enqueue(3);
    l.enqueue(4);
    assertEquals("peek gets element at front even after you add new stuff", 5, (int) l.peek());
}


@Test
public void enqueueDequeueWorks() {
    Queue<Integer> l = new TwoStacksQueue<Integer, LinkedListStack<Integer>>(LinkedListStack<Integer>::new);
    l.enqueue(1);
    assertEquals("dequeue pulls elements out in FIFO order", 1, (int) l.dequeue());
    l.enqueue(2);
    l.enqueue(3);
    assertEquals("dequeue pulls elements out in FIFO order", 2, (int) l.dequeue());
    l.enqueue(4);
    assertEquals("dequeue pulls elements out in FIFO order", 3, (int) l.dequeue());
    l.enqueue(5);
    assertEquals("dequeue pulls elements out in FIFO order", 4, (int) l.dequeue());
    assertEquals("dequeue pulls elements out in FIFO order", 5, (int) l.dequeue());
}

@Test
public void dequeueOnEmptyThrows() {
    Queue<Integer> l = new TwoStacksQueue<Integer, LinkedListStack<Integer>>(LinkedListStack<Integer>::new);
    assertThrows("dequeue throws on empty queue",
            NoSuchElementException.class, () -> l.dequeue());
    l.enqueue(1);
    l.dequeue();
    assertThrows("dequeue throws on empty queue",
            NoSuchElementException.class, () -> l.dequeue());
}
}