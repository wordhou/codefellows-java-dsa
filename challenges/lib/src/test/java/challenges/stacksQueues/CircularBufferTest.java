package challenges.stacksQueues;

import org.junit.Test;

import static org.junit.Assert.*;

public class CircularBufferTest {

    @Test
    public void constructorThrowsOnNonPositiveCapacity() {
        assertThrows("constructor throws on non-positive capacity",
                IllegalArgumentException.class, () -> new CircularBuffer<Integer>(0));
        assertThrows("constructor throws on non-positive capacity",
                IllegalArgumentException.class, () -> new CircularBuffer<Integer>(-1));
    }

    @Test
    public void isEmpty() throws CollectionIsFullException {
        BoundedQueue<Integer> l = new CircularBuffer<>(5);
        assertTrue("new circular buffer is empty", l.isEmpty());
        for (int i = 0; i < 10; i++) {
            l.enqueue(5);
            l.dequeue();
            assertTrue("isEmpty circular buffer", l.isEmpty());
        }
    }

    @Test
    public void isFull() throws CollectionIsFullException {
        BoundedQueue<Integer> l = new CircularBuffer<>(5);
        assertFalse("isFull on new circular buffer", l.isFull());
        l.enqueue(5);
        assertFalse("isFull on not full circular buffer", l.isFull());
        l.enqueue(5);
        assertFalse("isFull on not full circular buffer", l.isFull());
        l.enqueue(5);
        assertFalse("isFull on not full circular buffer", l.isFull());
        l.enqueue(5);
        assertFalse("isFull on not full circular buffer", l.isFull());
        l.enqueue(5);
        assertTrue("isFull on full circular buffer", l.isFull());
    }

    @Test
    public void offer() {
        BoundedQueue<Integer> l = new CircularBuffer<>(5);
        assertTrue("offer returns true when accepts item", l.offer(5));
        assertTrue("offer returns true when accepts item", l.offer(5));
        assertTrue("offer returns true when accepts item", l.offer(5));
        assertTrue("offer returns true when accepts item", l.offer(5));
        assertTrue("offer returns true when accepts item", l.offer(5));
        assertFalse("offer returns false when rejects item", l.offer(5));
        assertFalse("offer returns false when rejects item", l.offer(5));
        assertTrue("after offer rejects should be full", l.isFull());
    }

    @Test
    public void enqueue() throws CollectionIsFullException {
        BoundedQueue<Integer> l = new CircularBuffer<>(5);
        l.enqueue(5);
        assertFalse("after enqueue item is not empty", l.isEmpty());
        l.enqueue(5);
        l.enqueue(5);
        l.enqueue(5);
        l.enqueue(5);
        assertThrows("enqueue on full circular buffer throws",
                CollectionIsFullException.class, () -> l.enqueue(5));
    }

    @Test
    public void enqueueDequeue() throws CollectionIsFullException {
        BoundedQueue<Integer> l = new CircularBuffer<>(5);
        l.enqueue(1);
        l.enqueue(2);
        l.enqueue(3);
        l.enqueue(4);
        assertEquals("dequeue removes elements in FIFO order", 1, (int) l.dequeue());
        assertEquals("dequeue removes elements in FIFO order", 2, (int) l.dequeue());
        assertEquals("dequeue removes elements in FIFO order", 3, (int) l.dequeue());
        l.enqueue(5);
        l.enqueue(1);
        assertEquals("dequeue removes elements in FIFO order", 4, (int) l.dequeue());
        assertEquals("dequeue removes elements in FIFO order", 5, (int) l.dequeue());
        assertEquals("dequeue removes elements in FIFO order", 1, (int) l.dequeue());
        assertTrue("isEmpty", l.isEmpty());
        l.enqueue(2);
        l.enqueue(3);
        l.enqueue(4);
        l.enqueue(5);
        l.enqueue(1);
        assertTrue("isFull", l.isFull());
    }
}