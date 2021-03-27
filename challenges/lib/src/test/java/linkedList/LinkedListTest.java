package linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class LinkedListTest {
    @Test
    public void newListIsEmpty () {
        LinkedList<Integer> l = new LinkedList<>();
        assertTrue("new linked list is empty", l.isEmpty());
    }

    @Test
    public void insertMakesListNonEmpty () {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(5);
        assertFalse("linked list with elements is not empty", l.isEmpty());
    }

    @Test
    public void sizeUpdatesOnInsertAndRemove () throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        assertEquals("size of empty list", 0, l.size());
        l.insert(1);
        assertEquals("size of list after insert", 1, l.size());
        l.append(2);
        assertEquals("size of list after append", 2, l.size());
        l.append(3);
        l.append(4);
        assertEquals("size of list after append", 4, l.size());
        l.insert(5);
        l.insert(6);
        assertEquals("size of list after append", 6, l.size());
        l.removeFromEnd();
        assertEquals("size of list after removeFromEnd", 5, l.size());
        l.pop();
        l.pop();
        assertEquals("size of list after pop", 3, l.size());
        l.append(5);
        l.removeFromEnd();
        l.removeFromEnd();
        l.removeFromEnd();
        l.pop();
        assertEquals("size of list after inserts and removes", 0, l.size());
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
    public void toStringOnNonEmpty() {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(3);
        l.insert(2);
        l.insert(1);
        assertEquals(".toString() on empty list",
                "{ 1 } -> { 2 } -> { 3 } -> NULL", l.toString());
    }

    @Test
    public void popOnEmptyThrows() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        assertThrows(".pop() on empty list throws",
                NoSuchElementException.class, l::pop);
        l.insert(5);
        l.pop();
        assertThrows(".pop() on empty list throws",
                NoSuchElementException.class, l::pop);
    }

    @Test
    public void popRetrievesElementsLIFO() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(3);
        l.insert(2);
        l.insert(1);
        assertEquals(".pop() retrieves elements in last inserted first out order",
                1, (int) l.pop());
        assertEquals(".pop() retrieves elements in last inserted first out order",
                2, (int) l.pop());
        l.insert(5);
        l.insert(6);
        assertEquals(".pop() retrieves elements in last inserted first out order",
                6, (int) l.pop());
        assertEquals(".pop() retrieves elements in last inserted first out order",
                5, (int) l.pop());
        assertEquals(".pop() retrieves elements in last inserted first out order",
                3, (int) l.pop());
    }

    @Test
    public void removeFromEndOnEmptyThrows() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        assertThrows(".removeFromEnd() on empty list throws",
                NoSuchElementException.class, l::removeFromEnd);
        l.insert(1);
        l.pop();
        assertThrows(".removeFromEnd() on empty list throws",
                NoSuchElementException.class, l::removeFromEnd);
    }

    @Test
    public void removeFromEndTest1() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        l.insert(3);
        l.insert(2);
        l.insert(1);
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                3, (int) l.removeFromEnd());
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                2, (int) l.removeFromEnd());
        l.insert(5);
        l.insert(6);
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                1, (int) l.removeFromEnd());
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                5, (int) l.removeFromEnd());
        assertEquals(".removeFromEnd() retrieves elements in FIFO order",
                6, (int) l.removeFromEnd());
    }

    @Test
    public void insertIterables() {
        LinkedList<Integer> l = new LinkedList<>();
        Iterable<Integer> range = Arrays.asList(1, 2, 3, 4, 5);
        l.insert(range);
        for (int i = 5; i > 0; i--) {
            try {
                assertEquals("insert on iterable inserts elements in order",
                        i, (int) l.pop());
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                Assert.fail();
            }
        }
    }

    @Test
    public void appendIterables() throws NoSuchElementException {
        LinkedList<Integer> l = new LinkedList<>();
        Iterable<Integer> range = Arrays.asList(1, 2, 3, 4, 5);
        l.append(range);
        for (int i = 1; i < 6; i++) {
                assertEquals("append on iterable inserts elements in order",
                        i, (int) l.pop());
        }
    }

    @Test
    public void iterableInterface() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        List<Integer> range = Arrays.asList(test);
        for (Integer integer : test) l.append(integer);
        List<Integer> numbers = new ArrayList<>();
        int i = 0;
        for (Integer num : l) {
            assertEquals("iterable interface traverses list in order",
                    test[i], num);
            i++;
        }
    }

    @Test
    public void clearDeletesAllElements() {
        LinkedList<Integer> l = new LinkedList<>();
        l.append(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        l.clear();
        assertTrue("list is empty after clear", l.isEmpty());
        assertEquals("size of list is 0 after clear", 0, l.size());
    }

    @Test
    public void getIndexInBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        l.append(Arrays.asList(test));
        for (int i = 0; i < test.length; i++) {
            assertEquals("get finds elements by index starting from 0",
                    test[i], l.get(i));
        }
    }

    @Test
    public void getIndexOutOfBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(-1));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(1));
        l.append(Arrays.asList(test));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(-1));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.get(7));
    }

    @Test
    public void setIndexInBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        l.append(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        for (int i = 0; i < test.length; i++) l.set(i, test[i]);
        for (int i = 0; i < test.length; i++) {
            assertEquals(String.format("set() on index %d", i),
                    test[i], l.get(i));
        }
    }

    @Test
    public void setIndexOutOfBounds() {
        LinkedList<Integer> l = new LinkedList<>();
        Integer[] test = {1, 3, 2, 5, 4, 6, 7};
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(-1, 0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(0, 0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(1, 0));
        l.append(Arrays.asList(test));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(-1, 0));
        assertThrows("get with out of bounds index",
                IndexOutOfBoundsException.class, () -> l.set(7, 0));
    }
}
