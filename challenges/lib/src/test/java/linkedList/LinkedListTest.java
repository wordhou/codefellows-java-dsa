package linkedList;

import org.junit.Test;
import static org.junit.Assert.*;
import static java.util.Optional.ofNullable;

public class LinkedListTest {
    @Test
    public void newListIsEmpty () {
        LinkedList<Integer> l = new LinkedList<>();
        assertTrue("new linked list is empty", l.isEmpty());
    }

    @Test
    public void listWithElementsIsNotEmpty () {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(5);
        assertFalse("linked list with elements is not empty", l.isEmpty());
    }

    @Test
    public void includesElementFalse () {
        LinkedList<Integer> l = new LinkedList<>();
        assertFalse("empty linked list includes no element",
                l.includes(5));
        l.insert(10);
        assertFalse("linked list .includes() should be false when element not in list",
                l.includes(5));
        l.insert(15);
        assertFalse("linked list .includes() should be false when element not in list",
                l.includes(5));
    }

    @Test
    public void includesElementTrue () {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(5);
        assertTrue(".includes() is true when element in list", l.includes(5));
        l.insert(10);
        assertTrue(".includes() is true when element in list", l.includes(10));
        assertTrue(".includes() is true when element in list", l.includes(5));
        l.insert(15);
        assertTrue(".includes() is true when element in list", l.includes(15));
        assertTrue(".includes() is true when element in list", l.includes(10));
        assertTrue(".includes() is true when element in list", l.includes(5));
    }

    @Test
    public void toStringOnEmpty() {
        LinkedList<Integer> l = new LinkedList<>();
        assertEquals(".toString() on empty list",
                "NULL", l.toString());
    }

    @Test
    public void toStringOnListWithElements() {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(3);
        l.insert(2);
        l.insert(1);
        assertEquals(".toString() on empty list",
                "{ 1 } -> { 2 } -> { 3 } -> NULL", l.toString());
    }

    @Test
    public void popOnEmptyThrows() {
        LinkedList<Integer> l = new LinkedList();
        assertThrows(".pop() on empty list throws",
                EmptyListException.class, l::pop);
    }
}
