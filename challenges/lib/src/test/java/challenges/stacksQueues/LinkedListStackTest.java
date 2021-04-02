package challenges.stacksQueues;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedListStackTest {

    @Test
    public void isEmpty() {
        Stack<Integer> l = new LinkedListStack<>();
        assertTrue("new stack is empty", l.isEmpty());
        l.push(5);
        l.pop();
        assertTrue("isEmpty circular buffer", l.isEmpty());
    }

    @Test
    public void peek() {
        Stack<Integer> l = new LinkedListStack<>();
        assertNull("peek is null on empty list", l.peek());
        l.push(5);
        assertEquals("peek gets element at top when it exists", 5, (int) l.peek());
        l.push(3);
        l.push(4);
        assertEquals("peek gets element at top even after you add new stuff", 4, (int) l.peek());
    }

    @Test
    public void pushPopWorks() {
        Stack<Integer> l = new LinkedListStack<>();
        l.push(1);
        assertEquals("pop pulls elements out in FIFO order", 1, (int) l.pop());
        l.push(2);
        l.push(3);
        assertEquals("pop pulls elements out in FIFO order", 3, (int) l.pop());
        l.push(4);
        assertEquals("pop pulls elements out in FIFO order", 4, (int) l.pop());
        l.push(5);
        assertEquals("pop pulls elements out in FIFO order", 5, (int) l.pop());
        assertEquals("pop pulls elements out in FIFO order", 2, (int) l.pop());
    }

    @Test
    public void popOnEmptyThrows() {
        Stack<Integer> l = new LinkedListStack<>();
        assertThrows("pop throws on empty queue",
                NoSuchElementException.class, l::pop);
        l.push(1);
        l.pop();
        assertThrows("pop throws on empty queue",
                NoSuchElementException.class, l::pop);
    }
}