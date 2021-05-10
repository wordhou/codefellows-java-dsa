package challenges.stacksQueues;

import challenges.graph.IntGraph;
import challenges.graph.Traversable;
import challenges.graph.Traversals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class IntDynamicArrayTest {
    IntStack stack;

    @Before
    public void init() {
        stack = new IntDynamicArray();
    }

    @Test
    public void isEmpty() {
        assertTrue("new stack is empty", stack.isEmpty());
        stack.push(5);
        stack.pop();
        assertTrue("isEmpty circular buffer", stack.isEmpty());
    }

    @Test
    public void peek() {
        assertThrows("peek is null on empty list", NoSuchElementException.class, stack::peek);
        stack.push(5);
        assertEquals("peek gets element at top when it exists", 5, stack.peek());
        stack.push(3);
        stack.push(4);
        assertEquals("peek gets element at top even after you add new stuff", 4, stack.peek());
    }

    @Test
    public void pushPopWorks() {
        stack.push(1);
        assertEquals("pop pulls elements out in FIFO order", 1, stack.pop());
        stack.push(2);
        stack.push(3);
        assertEquals("pop pulls elements out in FIFO order", 3, stack.pop());
        stack.push(4);
        assertEquals("pop pulls elements out in FIFO order", 4, stack.pop());
        stack.push(5);
        assertEquals("pop pulls elements out in FIFO order", 5, stack.pop());
        assertEquals("pop pulls elements out in FIFO order", 2, stack.pop());
    }

    @Test
    public void popOnEmptyThrows() {
        assertThrows("pop throws on empty queue",
                Exception.class, stack::pop);
        stack.push(1);
        stack.pop();
        assertThrows("pop throws on empty queue",
                Exception.class, stack::pop);
    }
}