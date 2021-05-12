package challenges.stacksQueues;

import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class IndexedDoublePriorityQueueTest {

    @Test
    public void add() {
        IndexedDoublePriorityQueue pq = new IndexedDoublePriorityQueue(10);
        assertTrue(pq.isEmpty());
        assertThrows("Labels must be between 0 and n-1",
                ArrayIndexOutOfBoundsException.class, () -> pq.add(10, 1.0));
        assertThrows("Labels must be between 0 and n-1",
                ArrayIndexOutOfBoundsException.class, () -> pq.add(-1, 1.0));
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
        pq.add(5, 10.0);
        assertFalse(pq.isEmpty());
        assertEquals(1, pq.size());
        pq.add(5, 5.0);
        assertEquals(1, pq.size());
        pq.add(4, 5.0);
        assertEquals(2, pq.size());
    }

    @Test
    public void findMinIndex() {
        IndexedDoublePriorityQueue pq = new IndexedDoublePriorityQueue(13);
        assertThrows(NoSuchElementException.class, pq::findMinIndex);
        pq.add(0, 5);
        assertEquals(0, pq.findMinIndex());
        pq.add(1, 4);
        assertEquals(1, pq.findMinIndex());
        pq.add(2, 6);
        assertEquals(1, pq.findMinIndex());
        pq.add(3, 7);
        assertEquals(1, pq.findMinIndex());
        pq.add(4, 3);
        assertEquals(4, pq.findMinIndex());
        pq.add(5, 2);
        assertEquals(5, pq.findMinIndex());
        pq.add(6, 8);
        assertEquals(5, pq.findMinIndex());
        pq.add(7, 4);
        assertEquals(5, pq.findMinIndex());
        pq.add(8, 3);
        assertEquals(5, pq.findMinIndex());
        pq.add(9, 2);
        assertEquals(5, pq.findMinIndex());
        pq.add(10, 2);
        assertEquals(5, pq.findMinIndex());
        pq.add(11, 1);
        assertEquals(11, pq.findMinIndex());
        pq.add(12, 0);
        assertEquals(12, pq.findMinIndex());
    }

    @Test
    public void findMinWeight() {
        double delta = 1e-6;
        IndexedDoublePriorityQueue pq = new IndexedDoublePriorityQueue(13);
        assertThrows(NoSuchElementException.class, pq::findMinIndex);
        pq.add(0, 5);
        assertEquals(5.0, pq.findMinWeight(), delta);
        pq.add(1, 4);
        assertEquals(4.0, pq.findMinWeight(), delta);
        pq.add(2, 6);
        assertEquals(4.0, pq.findMinWeight(), delta);
        pq.add(3, 7);
        assertEquals(4.0, pq.findMinWeight(), delta);
        pq.add(4, 3);
        assertEquals(3.0, pq.findMinWeight(), delta);
        pq.add(5, 2);
        assertEquals(2.0, pq.findMinWeight(), delta);
        pq.add(6, 8);
        assertEquals(2.0, pq.findMinWeight(), delta);
        pq.add(7, 4);
        assertEquals(2.0, pq.findMinWeight(), delta);
        pq.add(8, 3);
        assertEquals(2.0, pq.findMinWeight(), delta);
        pq.add(9, 2);
        assertEquals(2.0, pq.findMinWeight(), delta);
        pq.add(10, 2);
        assertEquals(2.0, pq.findMinWeight(), delta);
        pq.add(11, 1);
        assertEquals(1.0, pq.findMinWeight(), delta);
        pq.add(12, 0);
        assertEquals(0.0, pq.findMinWeight(), delta);
    }

    @Test
    public void removeMin() {
        IndexedDoublePriorityQueue pq = new IndexedDoublePriorityQueue(13);
        assertThrows(NoSuchElementException.class, pq::removeMin);
        pq.add(0, 5);
        assertEquals(0, pq.removeMin());
        assertEquals(0, pq.size());
        pq.add(0, 5);
        pq.add(1, 4);
        pq.add(2, 6);
        assertEquals(1, pq.removeMin());
        assertEquals(2, pq.size());
        assertEquals(0, pq.findMinIndex());
    }

    @Test
    public void removeMinSequential() {
        IndexedDoublePriorityQueue pq = new IndexedDoublePriorityQueue(13);
        double[] values = {1, 7, 4, 3, 2, 6, 5, 1, 9, 8, 7, 2, 10};
        double[] sorted = values.clone();
        Arrays.sort(sorted);

        for (int i = 0; i < values.length; i++) {
            pq.add(i, values[i]);
        }

        for (int i = 0; i < values.length; i++) {
            assertEquals(sorted[i], pq.findMinWeight(), 1e-6);
            pq.removeMin();
        }
        assertTrue(pq.isEmpty());
    }

    @Test
    public void update() {
        IndexedDoublePriorityQueue pq = new IndexedDoublePriorityQueue(13);
        double[] values = {1, 7, 4, 3, 2, 6, 5, 1, 9, 8, 7, 2, 10};
        for (int i = 0; i < values.length; i++) {
            pq.add(i, values[i]);
        }
        assertThrows(Exception.class, () -> pq.update(5, 10));
        pq.update(5, 0);
        assertEquals(5, pq.findMinIndex());
        pq.update(2, -1);
        assertEquals(2, pq.findMinIndex());
        Arrays.asList(0,1,3,4,6,7,8,9,10,11,12).forEach(i -> pq.update(i, 0));
        pq.removeMin();
        pq.removeMin();
        for (int i = 2; i < values.length; i++) {
            assertEquals(0, pq.findMinWeight(), 1e-6);
            pq.removeMin();
        }
    }
}